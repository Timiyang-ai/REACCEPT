@Override
  @Deprecated
  public final OkHttpChannelBuilder usePlaintext(boolean skipNegotiation) {
    if (skipNegotiation) {
      negotiationType(io.grpc.okhttp.NegotiationType.PLAINTEXT);
    } else {
      throw new IllegalArgumentException("Plaintext negotiation not currently supported");
    }
    return this;
  }