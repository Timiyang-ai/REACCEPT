public synchronized String lockBlock(long blockId) throws IOException {
    mustConnect();

    try {
      return mClient.lockBlock(blockId, mUserId);
    } catch (FileDoesNotExistException e) {
      return null;
    } catch (TException e) {
      mConnected = false;
      throw new IOException(e);
    }
  }