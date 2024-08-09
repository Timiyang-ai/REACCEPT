@Override
  public V putIfAbsentFailure(K key, V value, StoreAccessException e) {
    cleanup(key, e);
    // FIXME: Should I care about useLoaderInAtomics?
    try {
      V loaded = loaderWriter.load(key);
      if(loaded != null) {
        return loaded;
      }
    } catch (Exception e1) {
      throw ExceptionFactory.newCacheLoadingException(e1, e);
    }
    try {
      loaderWriter.write(key, value);
    } catch (Exception e1) {
      throw ExceptionFactory.newCacheWritingException(e1, e);
    }
    return null;
  }