@Override
  public final OkHttpChannelBuilder usePlaintext() {
    negotiationType(NegotiationType.PLAINTEXT);
    return this;
  }