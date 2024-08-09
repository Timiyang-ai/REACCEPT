  @Test
  public void send() throws Exception {
    WriteRequest request = WriteRequest.newBuilder().build();

    mStream.send(request, TIMEOUT);

    verify(mRequestObserver).onNext(request);
  }