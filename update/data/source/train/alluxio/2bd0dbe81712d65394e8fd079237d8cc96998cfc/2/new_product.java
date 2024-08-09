@Override
  public void stop() throws IOException {
    mDataServer.close();

    mSessionCleanerThread.stop();
    mBlockMasterClient.close();
    if (mSpaceReserver != null) {
      mSpaceReserver.stop();
    }
    mFileSystemMasterClient.close();
    // Use shutdownNow because HeartbeatThreads never finish until they are interrupted
    getExecutorService().shutdownNow();
    mWorkerMetricsSystem.stop();
    try {
      mWebServer.shutdownWebServer();
    } catch (Exception e) {
      LOG.error("Failed to stop web server", e);
    }
    mBlockDataManager.stop();
    while (!mDataServer.isClosed()) {
      mDataServer.close();
      CommonUtils.sleepMs(100);
    }
  }