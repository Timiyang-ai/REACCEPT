@Override
  public void close() throws IOException {
    try {
      mLock.lock();
      if (mAvailableResources.size() != mResources.size()) {
        LOG.warn("{} resources are not released when closing the resource pool.",
            mResources.size() - mAvailableResources.size());
      }
      for (ResourceInternal<T> resourceInternal : mAvailableResources) {
        closeResource(resourceInternal.mResource);
      }
      mAvailableResources.clear();
    } finally {
      mLock.unlock();
    }
    mGcFuture.cancel(true);
  }