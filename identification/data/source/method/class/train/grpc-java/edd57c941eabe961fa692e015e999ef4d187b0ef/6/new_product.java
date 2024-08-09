@Override
  public NameResolver newNameResolver(URI targetUri, Attributes params) {
    for (NameResolver.Factory factory : registry) {
      NameResolver resolver = factory.newNameResolver(targetUri, params);
      if (resolver != null) {
        return resolver;
      }
    }
    return null;
  }