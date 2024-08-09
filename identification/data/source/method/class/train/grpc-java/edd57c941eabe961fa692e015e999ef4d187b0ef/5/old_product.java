@Nullable
    public NameResolver newNameResolver(URI targetUri, Helper helper) {
      return newNameResolver(
          targetUri,
          Attributes.newBuilder()
              .set(PARAMS_DEFAULT_PORT, helper.getDefaultPort())
              .set(PARAMS_PROXY_DETECTOR, helper.getProxyDetector())
              .set(PARAMS_SYNC_CONTEXT, helper.getSynchronizationContext())
              .build());
    }