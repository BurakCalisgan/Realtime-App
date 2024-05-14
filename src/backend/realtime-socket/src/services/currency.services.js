const Currency = require('../models/currency.model.js');

class CurrencyService {
  constructor(socketServer, database) {
    this.socketServer = socketServer;
    this.database = database;
    this.currencies = ['USD', 'EUR', 'GBP', 'JPY', 'CAD', 'AUD'];
  }

  startSendingData() {
    this.socketServer.startSendingData(this.sendRandomCurrencyData.bind(this));
  }

  sendRandomCurrencyData() {
    const currency = this.currencies[Math.floor(Math.random() * this.currencies.length)];
    const rate = (Math.random() * (10 - 1) + 1).toFixed(2);
    const data = new Currency(currency, rate);
    
    console.log(data);
    this.socketServer.emitData(data);
    //this.database.insertCurrencyData(currency, rate);
  }
}

module.exports = CurrencyService;