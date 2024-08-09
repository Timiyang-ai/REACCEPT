  @Test
  public void getSelectedProtocol_alpn() throws Exception {
    when(platform.getTlsExtensionType()).thenReturn(TlsExtensionType.ALPN_AND_NPN);
    AndroidNegotiator negotiator = new AndroidNegotiator(platform);
    FakeAndroidSslSocket androidSock = new FakeAndroidSslSocketAlpn();

    String actual = negotiator.getSelectedProtocol(androidSock);

    assertEquals("h2", actual);
  }