public synchronized void persistFile(long fileId, List<Long> blockIds) throws IOException {
    String dstPath = prepareUfsFilePath(fileId);
    OutputStream outputStream = mUfs.create(dstPath);
    final WritableByteChannel outputChannel = Channels.newChannel(outputStream);

    for (long blockId : blockIds) {
      long lockId;
      try {
        lockId = mBlockDataManager.lockBlock(Sessions.CHECKPOINT_SESSION_ID, blockId);
      } catch (BlockDoesNotExistException e) {
        throw new IOException(e);
      }

      // obtain block reader
      try {
        BlockReader reader;
        try {
          reader =
              mBlockDataManager.readBlockRemote(Sessions.CHECKPOINT_SESSION_ID, blockId, lockId);
        } catch (BlockDoesNotExistException e) {
          throw new IOException(e);
        } catch (InvalidWorkerStateException e) {
          throw new IOException(e);
        }

        // write content out
        ReadableByteChannel inputChannel = reader.getChannel();
        BufferUtils.fastCopy(inputChannel, outputChannel);
        reader.close();
      } finally {
        try {
          mBlockDataManager.unlockBlock(lockId);
        } catch (BlockDoesNotExistException e) {
          throw new IOException(e);
        }
      }
    }

    outputStream.flush();
    outputChannel.close();
    outputStream.close();
    mPersistedFiles.add(fileId);
  }