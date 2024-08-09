public ReasonCode getReason() {
    if (!this.unpacked) {
      this.unPack();
    }

    //TODO: fix this
    return ReasonCode.USER_REASON;
    //return this.disconnectMessage.getReason();
  }