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

  async insertCurrencyData(currency, rate) {
    const query = 'INSERT INTO currency_data(currency, rate) VALUES($1, $2)';
    const values = [currency, rate];
    
    try {
      const result = await this.client.query(query, values);
      console.log('Inserted currency data into PostgreSQL:', result.rows[0]);
    } catch (error) {
      console.error('Error inserting data into PostgreSQL:', error);
    }
  }
}

module.exports = Database;
