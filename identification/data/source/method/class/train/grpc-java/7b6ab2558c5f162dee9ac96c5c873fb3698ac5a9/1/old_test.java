  @Test
  public void createSslContextProvider_allFilenames() {
    DownstreamTlsContext downstreamTlsContext =
        SecretVolumeSslContextProviderTest.buildDownstreamTlsContextFromFilenames(
            SERVER_KEY_FILE, SERVER_PEM_FILE, CA_PEM_FILE);

    SslContextProvider<DownstreamTlsContext> sslContextProvider =
        serverSslContextProviderFactory.createSslContextProvider(downstreamTlsContext);
    assertThat(sslContextProvider).isNotNull();
  }