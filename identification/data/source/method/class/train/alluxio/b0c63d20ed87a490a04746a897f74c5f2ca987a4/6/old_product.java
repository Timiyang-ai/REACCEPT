public synchronized void cancelBlock(long blockId) throws IOException {
    mustConnect();

    try {
      mClient.cancelBlock(mMasterClient.getUserId(), blockId);
    } catch (TException e) {
      mConnected = false;
      throw new IOException(e);
    }
  }