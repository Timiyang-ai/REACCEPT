public Message.ReasonCode getReason() {
    if (!parsed) {
      parse();
    }
    return this.disconnectMessage.getReason();
  }