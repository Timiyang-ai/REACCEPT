@Nullable
    @Deprecated
    public NameResolver newNameResolver(URI targetUri, final Attributes params) {
      Args args = Args.newBuilder()
          .setDefaultPort(params.get(PARAMS_DEFAULT_PORT))
          .setProxyDetector(params.get(PARAMS_PROXY_DETECTOR))
          .setSynchronizationContext(params.get(PARAMS_SYNC_CONTEXT))
          .setServiceConfigParser(params.get(PARAMS_PARSER))
          .build();
      return newNameResolver(targetUri, args);
    }