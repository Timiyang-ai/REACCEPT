@Override
  public void removeAllFailure(Iterable<? extends K> keys, StoreAccessException e) {
    try {
      loaderWriter.deleteAll(keys);
    } catch(BulkCacheWritingException e1) {
      throw e1;
    } catch (Exception e1) {
      throw ExceptionFactory.newCacheWritingException(e1, e);
    } finally {
      cleanup(keys, e);
    }
  }