public void persistFile(long fileId, List<Long> blockIds) throws IOException {
    Map<Long, Long> blockIdToLockId;
    synchronized (mLock) {
      blockIdToLockId = mPersistingInProgressFiles.get(fileId);
      if (blockIdToLockId == null || blockIdToLockId.size() != blockIds.size()) {
        throw new IOException("Not all the blocks of file " + fileId + " are blocked");
      }
    }

    String dstPath = prepareUfsFilePath(fileId);
    OutputStream outputStream = mUfs.create(dstPath);
    final WritableByteChannel outputChannel = Channels.newChannel(outputStream);

    List<Throwable> errors = new ArrayList<Throwable>();
    try {
      for (long blockId : blockIds) {
        long lockId = blockIdToLockId.get(blockId);

        // obtain block reader
        BlockReader reader =
            mBlockDataManager.readBlockRemote(Sessions.CHECKPOINT_SESSION_ID, blockId, lockId);

        // write content out
        ReadableByteChannel inputChannel = reader.getChannel();
        BufferUtils.fastCopy(inputChannel, outputChannel);
        reader.close();
      }
    } catch (BlockDoesNotExistException e) {
      errors.add(e);
    } catch (InvalidWorkerStateException e) {
      errors.add(e);
    } finally {
      // make sure all the locks are released
      for (long lockId : blockIdToLockId.values()) {
        try {
          mBlockDataManager.unlockBlock(lockId);
        } catch (BlockDoesNotExistException bdnee) {
          errors.add(bdnee);
        }
      }

      if (!errors.isEmpty()) {
        StringBuilder errorStr = new StringBuilder();
        errorStr.append("the blocks of file").append(fileId).append(" are failed to persist\n");
        for (Throwable e : errors) {
          errorStr.append(e).append('\n');
        }
        throw new IOException(errorStr.toString());
      }
    }

    outputStream.flush();
    outputChannel.close();
    outputStream.close();
    synchronized (mLock) {
      mPersistingInProgressFiles.remove(fileId);
      mPersistedFiles.add(fileId);
    }
  }