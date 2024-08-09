public synchronized void cancelBlock(long blockId) throws IOException {
    mustConnect();

    try {
      mClient.cancelBlock(mUserId, blockId);
    } catch (TException e) {
      mConnected = false;
      throw new IOException(e);
    }
  }