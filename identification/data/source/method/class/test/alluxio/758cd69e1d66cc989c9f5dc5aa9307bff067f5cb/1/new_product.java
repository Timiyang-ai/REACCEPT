public synchronized boolean deleteFile(long fileId, boolean recursive) throws IOException,
      TachyonException {
    int retry = 0;
    while (!mClosed && (retry ++) <= RPC_MAX_NUM_RETRY) {
      connect();
      try {
        return mClient.deleteFile(fileId, recursive);
      } catch (TachyonTException e) {
        throw new TachyonException(e);
      } catch (TException e) {
        LOG.error(e.getMessage(), e);
        mConnected = false;
      }
    }
    throw new IOException("Failed after " + retry + " retries.");
  }