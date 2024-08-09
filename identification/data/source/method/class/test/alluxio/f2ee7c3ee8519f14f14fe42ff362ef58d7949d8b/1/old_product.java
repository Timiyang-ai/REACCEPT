public void persistFile(long fileId, List<Long> blockIds) throws AlluxioException, IOException {
    Map<Long, Long> blockIdToLockId;
    synchronized (mLock) {
      blockIdToLockId = mPersistingInProgressFiles.get(fileId);
      if (blockIdToLockId == null || !blockIdToLockId.keySet().equals(new HashSet<>(blockIds))) {
        throw new IOException("Not all the blocks of file " + fileId + " are locked");
      }
    }

    String dstPath = prepareUfsFilePath(fileId);
    FileInfo fileInfo = mBlockWorker.getFileInfo(fileId);
    UnderFileSystem ufs = mUfsManager.get(fileInfo.getMountId()).getUfs();
    OutputStream outputStream = ufs.create(dstPath, CreateOptions.defaults()
        .setOwner(fileInfo.getOwner()).setGroup(fileInfo.getGroup())
        .setMode(new Mode((short) fileInfo.getMode())));
    final WritableByteChannel outputChannel = Channels.newChannel(outputStream);

    List<Throwable> errors = new ArrayList<>();
    try {
      for (long blockId : blockIds) {
        long lockId = blockIdToLockId.get(blockId);

        if (Configuration.getBoolean(PropertyKey.WORKER_FILE_PERSIST_RATE_LIMIT_ENABLED)) {
          BlockMeta blockMeta =
              mBlockWorker.getBlockMeta(Sessions.CHECKPOINT_SESSION_ID, blockId, lockId);
          mPersistenceRateLimiter.acquire((int) blockMeta.getBlockSize());
        }

        // obtain block reader
        BlockReader reader =
            mBlockWorker.readBlockRemote(Sessions.CHECKPOINT_SESSION_ID, blockId, lockId);

        // write content out
        ReadableByteChannel inputChannel = reader.getChannel();
        BufferUtils.fastCopy(inputChannel, outputChannel);
        reader.close();
      }
    } catch (BlockDoesNotExistException | InvalidWorkerStateException e) {
      errors.add(e);
    } finally {
      // make sure all the locks are released
      for (long lockId : blockIdToLockId.values()) {
        try {
          mBlockWorker.unlockBlock(lockId);
        } catch (BlockDoesNotExistException e) {
          errors.add(e);
        }
      }

      // Process any errors
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
    UfsFileStatus ufsFileStatus = ufs.getFileStatus(dstPath);
    synchronized (mLock) {
      mPersistingInProgressFiles.remove(fileId);
      mPersistedFilesInfo.put(fileId, ufsFileStatus);
    }
  }