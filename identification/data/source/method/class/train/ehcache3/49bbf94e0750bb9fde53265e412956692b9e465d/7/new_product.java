@SuppressWarnings("unchecked")
  @Override
  public Map<K, V> getAllFailure(Iterable<? extends K> keys, StoreAccessException e) {
    try {
      return loaderWriter.loadAll((Iterable) keys); // FIXME: bad typing that we should fix
    } catch(BulkCacheLoadingException e1) {
      throw e1;
    } catch (Exception e1) {
      throw ExceptionFactory.newCacheLoadingException(e1, e);
    } finally {
      cleanup(keys, e);
    }
  }