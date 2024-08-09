  public org.tron.protos.Protocol.ReasonCode getReason() {
    org.tron.protos.Protocol.ReasonCode result = org.tron.protos.Protocol.ReasonCode
        .valueOf(reason_);
    return result == null ? org.tron.protos.Protocol.ReasonCode.UNRECOGNIZED : result;
  }