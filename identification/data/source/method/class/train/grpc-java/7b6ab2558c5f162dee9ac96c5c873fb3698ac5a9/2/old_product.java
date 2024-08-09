@Override
  public SslContextProvider<UpstreamTlsContext> createSslContextProvider(
      UpstreamTlsContext upstreamTlsContext) {
    checkNotNull(upstreamTlsContext, "upstreamTlsContext");
    checkArgument(
        upstreamTlsContext.hasCommonTlsContext(),
        "upstreamTlsContext should have CommonTlsContext");
    if (CommonTlsContextUtil.hasAllSecretsUsingFilename(upstreamTlsContext.getCommonTlsContext())) {
      return SecretVolumeSslContextProvider.getProviderForClient(upstreamTlsContext);
    }
    throw new UnsupportedOperationException("UpstreamTlsContext using SDS not supported");
  }