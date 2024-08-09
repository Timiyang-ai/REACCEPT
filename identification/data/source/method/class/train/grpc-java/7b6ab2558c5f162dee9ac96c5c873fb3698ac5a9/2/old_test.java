  @Test
  public void createSslContextProvider_allFilenames() {
    UpstreamTlsContext upstreamTlsContext =
        SecretVolumeSslContextProviderTest.buildUpstreamTlsContextFromFilenames(
            CLIENT_KEY_FILE, CLIENT_PEM_FILE, CA_PEM_FILE);

    SslContextProvider<UpstreamTlsContext> sslContextProvider =
        clientSslContextProviderFactory.createSslContextProvider(upstreamTlsContext);
    assertThat(sslContextProvider).isNotNull();
  }