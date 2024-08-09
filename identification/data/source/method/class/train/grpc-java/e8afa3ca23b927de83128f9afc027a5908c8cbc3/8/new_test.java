  @CanIgnoreReturnValue
  private ChannelFuture createStream() throws Exception {
    ChannelFuture future = enqueue(newCreateStreamCommand(grpcHeaders, streamTransportState));
    return future;
  }