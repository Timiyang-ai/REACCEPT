public synchronized void cacheBlock(long blockId) throws IOException {
    mustConnect();

    try {
      mClient.cacheBlock(mMasterClient.getUserId(), blockId);
    } catch (FileDoesNotExistException e) {
      throw new IOException(e);
    } catch (BlockInfoException e) {
      throw new IOException(e);
    } catch (TException e) {
      mConnected = false;
      throw new IOException(e);
    }
  }