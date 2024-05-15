const { Client } = require('pg');

class Database {
  constructor(connectionString) {
    this.client = new Client({
      user: connectionString.user,
      host: connectionString.host,
      database: connectionString.database,
      password: connectionString.password,
      port: connectionString.port
    });
  }

  async connect() {
    try {
      await this.client.connect();
      console.log('Connected to PostgreSQL database');
    } catch (error) {
      console.error('Error connecting to PostgreSQL database:', error);
    }
  }

  async insertSymbolData(symbol, buyPrice, sellPrice) {
    const query = 'INSERT INTO symbols(symbol, buy_price, sell_price) VALUES($1, $2, $2)';
    const values = [symbol, buyPrice, sellPrice];
    
    try {
      const result = await this.client.query(query, values);
      console.log('Inserted symbol data into PostgreSQL:', result.rows[0]);
    } catch (error) {
      console.error('Error inserting data into PostgreSQL:', error);
    }
  }
}

module.exports = Database;
