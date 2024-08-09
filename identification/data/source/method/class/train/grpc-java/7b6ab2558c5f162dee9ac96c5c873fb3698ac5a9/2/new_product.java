@Override
  public SslContextProvider<UpstreamTlsContext> createSslContextProvider(
      UpstreamTlsContext upstreamTlsContext) {
    checkNotNull(upstreamTlsContext, "upstreamTlsContext");
    checkArgument(
        upstreamTlsContext.hasCommonTlsContext(),
        "upstreamTlsContext should have CommonTlsContext");
    if (CommonTlsContextUtil.hasAllSecretsUsingFilename(upstreamTlsContext.getCommonTlsContext())) {
      return SecretVolumeSslContextProvider.getProviderForClient(upstreamTlsContext);
    } else if (CommonTlsContextUtil.hasAllSecretsUsingSds(
        upstreamTlsContext.getCommonTlsContext())) {
      try {
        return SdsSslContextProvider.getProviderForClient(
            upstreamTlsContext,
            Bootstrapper.getInstance().readBootstrap().getNode(),
            Executors.newSingleThreadExecutor(),
            /* channelExecutor= */ null);
      } catch (IOException ioe) {
        throw new RuntimeException(ioe);
      }
    }
    throw new UnsupportedOperationException(
        "UpstreamTlsContext to have all filenames or all SdsConfig");
  }