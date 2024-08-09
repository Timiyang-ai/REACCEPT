  @Test public void nextSpan_samplerSeesHttpClientRequest() {
    defaultHandler.nextSpan(defaultRequest);

    verify(requestSampler).trySample(defaultRequest);
  }