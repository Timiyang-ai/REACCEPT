  @Test
  public void inboundDataReceived_failsOnNullFrame() {
    ClientStreamListener listener = new NoopClientStreamListener();
    AbstractClientStream stream =
        new BaseAbstractClientStream(allocator, statsTraceCtx, transportTracer);
    stream.start(listener);

    TransportState state = stream.transportState();

    thrown.expect(NullPointerException.class);
    state.inboundDataReceived(null);
  }