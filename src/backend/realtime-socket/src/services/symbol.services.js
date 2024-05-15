const Symbol = require('../models/symbol.model.js');

class SymbolService {
  constructor(socketServer, database) {
    this.socketServer = socketServer;
    this.database = database;
    this.symbols = ['USD/TRY', 'EUR/TRY', 'AUD/TRY', 'AUD/USD'];
  }

  startSendingData() {
    this.socketServer.startSendingData(this.sendRandomSymbolData.bind(this));
  }

  sendRandomSymbolData() {
    const symbol = this.symbols[Math.floor(Math.random() * this.symbols.length)];
    const buyPrice = (Math.random() * (10 - 1) + 1).toFixed(2);
    const sellPrice = (Math.random() * (10 - 1) + 1).toFixed(2);
    const data = new Symbol(symbol, buyPrice, sellPrice);
    
    console.log(data);
    this.socketServer.emitData(data);
    //this.database.insertSymbolData(symbol, buyPrice, sellPrice);
  }
}

module.exports = SymbolService;