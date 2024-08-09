  @Test
  public void transportHeadersReceived_notifiesListener() {
    BaseTransportState state = new BaseTransportState(transportTracer);
    state.setListener(mockListener);
    Metadata headers = new Metadata();
    headers.put(testStatusMashaller, "200");
    headers.put(Metadata.Key.of("content-type", Metadata.ASCII_STRING_MARSHALLER),
        "application/grpc");
    state.transportHeadersReceived(headers);

    verify(mockListener, never()).closed(any(Status.class), same(PROCESSED), any(Metadata.class));
    verify(mockListener).headersRead(headers);
  }