  @Test
  public void updateHeaders() {
    Metadata originalHeaders = new Metadata();
    Metadata headers = retriableStream.updateHeaders(originalHeaders, 0);
    assertNotSame(originalHeaders, headers);
    assertNull(headers.get(GRPC_PREVIOUS_RPC_ATTEMPTS));

    headers = retriableStream.updateHeaders(originalHeaders, 345);
    assertEquals("345", headers.get(GRPC_PREVIOUS_RPC_ATTEMPTS));
    assertNull(originalHeaders.get(GRPC_PREVIOUS_RPC_ATTEMPTS));
  }