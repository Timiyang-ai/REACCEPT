@Override
  public V replaceFailure(K key, V value, StoreAccessException e) {
    try {
      V oldValue;
      try {
        oldValue = loaderWriter.load(key);
      } catch (Exception e1) {
        throw ExceptionFactory.newCacheLoadingException(e1, e);
      }

      if (oldValue != null) {
        try {
          loaderWriter.write(key, value);
        } catch (Exception e1) {
          throw ExceptionFactory.newCacheWritingException(e1, e);
        }
      }
      return oldValue;
    } finally {
      cleanup(key, e);
    }

  }