const socketIo = require('socket.io');

class SocketServer {
  constructor(server) {
    this.io = socketIo(server);
  }

  startSendingData(callback) {
    setInterval(callback, 3000);
  }

  emitData(data) {
    this.io.emit('currencyData', data);
  }
}

module.exports = SocketServer;