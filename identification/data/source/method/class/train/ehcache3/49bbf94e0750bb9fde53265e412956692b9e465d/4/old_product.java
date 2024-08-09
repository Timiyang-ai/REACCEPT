@Override
  public boolean replaceFailure(K key, V value, V newValue, StoreAccessException e) {
    cleanup(key, e);

    V oldValue;
    try {
      oldValue = loaderWriter.load(key);
    } catch(Exception e1) {
      throw ExceptionFactory.newCacheLoadingException(e1, e);
    }

    if (oldValue != null && oldValue.equals(value)) {
      try {
        loaderWriter.write(key, newValue);
        return true;
      } catch(Exception e1) {
        throw ExceptionFactory.newCacheWritingException(e1, e);
      }
    }

    return false;
  }