const express = require('express');
const http = require('http');
const cors = require('cors');
const Database = require('./infrastructure/database.js');
const SocketServer = require('./infrastructure/socket.js');
const CurrencyService = require('./services/currency.services.js');

const app = express();
const server = http.createServer(app);
app.use(cors());

// Soket sunucusu başlatma
const socketServer = new SocketServer(server);

// PostgreSQL bağlantısı
const database = new Database();
database.connect();


// Currency servisini oluşturma ve başlatma
const currencyService = new CurrencyService(socketServer, database);
currencyService.startSendingData();

const PORT = process.env.PORT || 3000;

server.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});