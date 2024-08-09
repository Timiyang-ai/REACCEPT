@Nullable
    @Deprecated
    public NameResolver newNameResolver(URI targetUri, final Attributes params) {
      Helper helper = new Helper() {
          @Override
          public int getDefaultPort() {
            return checkNotNull(params.get(PARAMS_DEFAULT_PORT), "default port not available");
          }

          @Override
          public ProxyDetector getProxyDetector() {
            return checkNotNull(params.get(PARAMS_PROXY_DETECTOR), "proxy detector not available");
          }

          @Override
          public SynchronizationContext getSynchronizationContext() {
            return checkNotNull(params.get(PARAMS_SYNC_CONTEXT), "sync context not available");
          }
        };
      return newNameResolver(targetUri, helper);
    }