  @Test
  public void transportDataReceived_noHeaderReceived() {
    BaseTransportState state = new BaseTransportState(transportTracer);
    state.setListener(mockListener);
    String testString = "This is a test";
    state.transportDataReceived(ReadableBuffers.wrap(testString.getBytes(US_ASCII)), true);

    verify(mockListener).closed(statusCaptor.capture(), same(PROCESSED), any(Metadata.class));
    assertEquals(Code.INTERNAL, statusCaptor.getValue().getCode());
  }