  @Deprecated
  @Test
  public void newNameResolver_Api2DelegatesToApi1() {
    final AtomicReference<Attributes> paramsCapture = new AtomicReference<>();
    NameResolver.Factory factory = new NameResolver.Factory() {
        @Deprecated
        @Override
        public NameResolver newNameResolver(URI targetUri, Attributes params) {
          assertThat(targetUri).isSameInstanceAs(uri);
          paramsCapture.set(params);
          return nameResolver;
        }

        @Override
        public String getDefaultScheme() {
          throw new AssertionError();
        }
      };
    assertThat(factory.newNameResolver(uri, new NameResolver.Helper() {
        @Override
        public int getDefaultPort() {
          return defaultPort;
        }

        @Override
        public ProxyDetector getProxyDetector() {
          return proxyDetector;
        }

        @Override
        public SynchronizationContext getSynchronizationContext() {
          return syncContext;
        }
      })).isSameInstanceAs(nameResolver);
    Attributes params = paramsCapture.get();
    assertThat(params.get(NameResolver.Factory.PARAMS_DEFAULT_PORT)).isEqualTo(defaultPort);
    assertThat(params.get(NameResolver.Factory.PARAMS_PROXY_DETECTOR))
        .isSameInstanceAs(proxyDetector);
  }