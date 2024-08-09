@Override
  public void removeAllFailure(Iterable<? extends K> entries, StoreAccessException e) {
    cleanup(entries, e);

    try {
      loaderWriter.deleteAll(entries);
    } catch(BulkCacheWritingException e1) {
      throw e1;
    } catch (Exception e1) {
      throw ExceptionFactory.newCacheWritingException(e1, e);
    }
  }