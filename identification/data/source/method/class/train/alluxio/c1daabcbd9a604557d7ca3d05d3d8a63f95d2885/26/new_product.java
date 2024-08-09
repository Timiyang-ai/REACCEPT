public synchronized void close() throws IOException {
    if (!mClosed.get()) {
      mFileSystemMasterClientPool.close();
      mFileSystemMasterClientPool = null;
      mBlockMasterClientPool.close();
      mBlockMasterClientPool = null;
      mMasterInquireClient = null;

      if (mMetricsMasterClient != null) {
        ThreadUtils.shutdownAndAwaitTermination(mExecutorService,
            mClientContext.getConf().getMs(PropertyKey.METRICS_CONTEXT_SHUTDOWN_TIMEOUT));
        mMetricsMasterClient.close();
        mMetricsMasterClient = null;
        mClientMasterSync = null;
      }
      mLocalWorkerInitialized = false;
      mLocalWorker = null;
      mClosed.set(true);
    } else {
      LOG.warn("Attempted to close FileSystemContext with app ID {} which has already been closed"
          + " or not initialized.", mAppId);
    }
  }