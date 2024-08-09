  @Test
  public void inboundHeadersReceived_notifiesListener() {
    AbstractClientStream stream =
        new BaseAbstractClientStream(allocator, statsTraceCtx, transportTracer);
    stream.start(mockListener);
    Metadata headers = new Metadata();

    stream.transportState().inboundHeadersReceived(headers);
    verify(mockListener).headersRead(headers);
  }