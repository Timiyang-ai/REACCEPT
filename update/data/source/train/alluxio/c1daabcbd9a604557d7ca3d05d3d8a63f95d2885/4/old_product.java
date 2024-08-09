@Override
  public void close() throws IOException {
    synchronized (CONTEXT_CACHE_LOCK) {
      if (mRefCount == 0) {
        LOG.warn("Attempted to close FileSystem Context that is already closed, have you called "
            + "close multiple times?");
        return;
      }
      if (--mRefCount != 0) {
        return;
      } else {
        CONTEXT_CACHE.remove(mParentSubject);
      }
    }
    closeInternal();
  }