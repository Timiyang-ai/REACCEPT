  @Test
  public void negotiate_handshakeFails() throws IOException {
    SSLParameters parameters = new SSLParameters();
    OkHttpProtocolNegotiator negotiator = OkHttpProtocolNegotiator.get();
    doReturn(parameters).when(sock).getSSLParameters();
    doThrow(new IOException()).when(sock).startHandshake();
    thrown.expect(IOException.class);

    negotiator.negotiate(sock, "hostname", ImmutableList.of(Protocol.HTTP_2));
  }