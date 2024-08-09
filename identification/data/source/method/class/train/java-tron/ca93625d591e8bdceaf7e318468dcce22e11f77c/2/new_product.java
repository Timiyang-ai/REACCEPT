public Message.ReasonCode getReason() {
    if (!this.unpacked) {
      this.unPack();
    }
    return this.disconnectMessage.getReason();
  }