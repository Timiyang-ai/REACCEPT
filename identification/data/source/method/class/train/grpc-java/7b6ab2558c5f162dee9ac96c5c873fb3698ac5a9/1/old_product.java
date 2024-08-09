@Override
  public SslContextProvider<DownstreamTlsContext> createSslContextProvider(
      DownstreamTlsContext downstreamTlsContext) {
    checkNotNull(downstreamTlsContext, "downstreamTlsContext");
    checkArgument(
        downstreamTlsContext.hasCommonTlsContext(),
        "downstreamTlsContext should have CommonTlsContext");
    if (CommonTlsContextUtil.hasAllSecretsUsingFilename(
        downstreamTlsContext.getCommonTlsContext())) {
      return SecretVolumeSslContextProvider.getProviderForServer(downstreamTlsContext);
    }
    throw new UnsupportedOperationException("DownstreamTlsContext using SDS not supported");
  }