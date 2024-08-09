@Override
  public boolean removeFailure(K key, V value, StoreAccessException e) {
    try {
      V loadedValue;

      try {
        loadedValue = loaderWriter.load(key);
      } catch (Exception e1) {
        throw ExceptionFactory.newCacheLoadingException(e1, e);
      }

      if (loadedValue == null) {
        return false;
      }
      if (!loadedValue.equals(value)) {
        return false;
      }

      try {
        loaderWriter.delete(key);
      } catch (Exception e1) {
        throw ExceptionFactory.newCacheWritingException(e1, e);
      }
      return true;
    } finally {
      cleanup(key, e);
    }
  }