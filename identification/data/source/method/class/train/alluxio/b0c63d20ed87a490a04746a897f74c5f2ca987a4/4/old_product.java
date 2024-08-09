public synchronized String lockBlock(long blockId) throws IOException {
    mustConnect();

    try {
      return mClient.lockBlock(blockId, mMasterClient.getUserId());
    } catch (FileDoesNotExistException e) {
      return null;
    } catch (TException e) {
      mConnected = false;
      throw new IOException(e);
    }
  }