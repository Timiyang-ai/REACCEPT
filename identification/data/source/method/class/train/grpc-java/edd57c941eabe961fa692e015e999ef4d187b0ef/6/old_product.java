@Override
  public NameResolver newNameResolver(URI targetUri) {
    for (NameResolver.Factory factory : registry) {
      NameResolver resolver = factory.newNameResolver(targetUri);
      if (resolver != null) {
        return resolver;
      }
    }
    return null;
  }