  @Test
  public void newNameResolver_providerReturnsNull() {
    NameResolverRegistry registry = new NameResolverRegistry();
    registry.register(
        new BaseProvider(true, 5) {
          @Override
          public NameResolver newNameResolver(URI passedUri, NameResolver.Args passedArgs) {
            assertThat(passedUri).isSameInstanceAs(uri);
            assertThat(passedArgs).isSameInstanceAs(args);
            return null;
          }
        });
    assertThat(registry.asFactory().newNameResolver(uri, args)).isNull();
  }