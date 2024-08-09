  @Test
  public void transportTrailersReceived_notifiesListener() {
    BaseTransportState state = new BaseTransportState(transportTracer);
    state.setListener(mockListener);
    Metadata trailers = new Metadata();
    trailers.put(testStatusMashaller, "200");
    trailers.put(Metadata.Key.of("content-type", Metadata.ASCII_STRING_MARSHALLER),
        "application/grpc");
    trailers.put(Metadata.Key.of("grpc-status", Metadata.ASCII_STRING_MARSHALLER), "0");
    state.transportTrailersReceived(trailers);

    verify(mockListener, never()).headersRead(any(Metadata.class));
    verify(mockListener).closed(Status.OK, PROCESSED, trailers);
  }