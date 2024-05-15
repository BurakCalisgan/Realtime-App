const express = require('express');
const http = require('http');
const cors = require('cors');
const fs = require('fs');
const path = require('path');
const dotenv = require('dotenv');
const Database = require('./infrastructure/database.js');
const SocketServer = require('./infrastructure/socket.js');
const SymbolService = require('./services/symbol.services.js');

const app = express();
const server = http.createServer(app);
app.use(cors());
// NODE_ENV değişkenine göre environment dosyasını yükleme
const envFile = path.resolve(__dirname, 'environments', `.env.${process.env.NODE_ENV || 'development'}`);

if (fs.existsSync(envFile)) {
  dotenv.config({ path: envFile });
} else {
  console.error(`Environment file ${envFile} not found!`);
  process.exit(1);
}

// Soket sunucusu başlatma
const socketServer = new SocketServer(server);

// PostgreSQL bağlantısı
connectionString = {
  user: process.env.POSTGRES_DB_USER,
  host: process.env.POSTGRES_DB_HOST,
  database: process.env.POSTGRES_DB_DATABASE,
  password: process.env.POSTGRES_DB_PASSWORD,
  port: process.env.POSTGRES_DB_PORT
};

console.log(connectionString);
const database = new Database(connectionString);
database.connect();


// Symbol servisini oluşturma ve başlatma
const symbolService = new SymbolService(socketServer, database);
symbolService.startSendingData();

const PORT = process.env.PORT || 3000;

server.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});