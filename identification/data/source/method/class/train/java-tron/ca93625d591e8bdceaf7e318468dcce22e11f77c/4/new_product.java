public int getReason() {
    if (!this.unpacked) {
      this.unPack();
    }

    return this.disconnectMessage.getReason().getNumber();
  }