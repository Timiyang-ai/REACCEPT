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
    } else if (CommonTlsContextUtil.hasAllSecretsUsingSds(
        downstreamTlsContext.getCommonTlsContext())) {
      try {
        return SdsSslContextProvider.getProviderForServer(
            downstreamTlsContext,
            Bootstrapper.getInstance().readBootstrap().getNode(),
            Executors.newSingleThreadExecutor(),
            /* channelExecutor= */ null);
      } catch (IOException ioe) {
        throw new RuntimeException(ioe);
      }
    }
    throw new UnsupportedOperationException(
        "DownstreamTlsContext to have all filenames or all SdsConfig");
  }