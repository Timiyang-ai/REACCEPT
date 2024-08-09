@Deprecated
    @Nullable
    public NameResolver newNameResolver(URI targetUri, final Helper helper) {
      return newNameResolver(
          targetUri,
          Attributes.newBuilder()
              .set(PARAMS_DEFAULT_PORT, helper.getDefaultPort())
              .set(PARAMS_PROXY_DETECTOR, helper.getProxyDetector())
              .set(PARAMS_SYNC_CONTEXT, helper.getSynchronizationContext())
              .set(PARAMS_PARSER, new ServiceConfigParser() {
                  @Override
                  public ConfigOrError parseServiceConfig(Map<String, ?> rawServiceConfig) {
                    return helper.parseServiceConfig(rawServiceConfig);
                  }
                })
              .build());
    }