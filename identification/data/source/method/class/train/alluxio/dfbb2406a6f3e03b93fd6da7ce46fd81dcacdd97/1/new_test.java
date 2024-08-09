  @Test
  public void receive() throws Exception {
    WriteResponse response = WriteResponse.newBuilder().build();
    mResponseObserver.onNext(response);

    WriteResponse actualResponse = mStream.receive(TIMEOUT);

    assertEquals(response, actualResponse);
  }