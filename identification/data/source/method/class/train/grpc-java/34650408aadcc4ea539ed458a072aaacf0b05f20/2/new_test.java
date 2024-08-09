  @CanIgnoreReturnValue
  private ChannelFuture cancelStream(Status status) throws Exception {
    return enqueue(new CancelClientStreamCommand(streamTransportState, status));
  }