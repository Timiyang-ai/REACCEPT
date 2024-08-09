  @Test
  public void httpProxy_nullAddressNpe() throws Exception {
    thrown.expect(NullPointerException.class);
    Object unused =
        ProtocolNegotiators.httpProxy(null, "user", "pass", ProtocolNegotiators.plaintext());
  }