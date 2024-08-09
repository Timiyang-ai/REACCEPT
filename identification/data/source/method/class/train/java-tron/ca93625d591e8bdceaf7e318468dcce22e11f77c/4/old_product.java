public byte getReason() {
    if (!this.unpacked) {
      this.unPack();
    }

    return ReasonCode.fromInt(this.disconnectMessage.getReason().getNumber()).asByte();
  }