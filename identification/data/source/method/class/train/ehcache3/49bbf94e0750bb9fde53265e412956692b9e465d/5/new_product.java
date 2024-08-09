@Override
  public void putAllFailure(Map<? extends K, ? extends V> entries, StoreAccessException e) {
    try {
      loaderWriter.writeAll(entries.entrySet()); // FIXME: bad typing that we should fix
    } catch(BulkCacheWritingException e1) {
      throw e1;
    } catch (Exception e1) {
      throw ExceptionFactory.newCacheWritingException(e1, e);
    } finally {
      cleanup(entries.keySet(), e);
    }
  }