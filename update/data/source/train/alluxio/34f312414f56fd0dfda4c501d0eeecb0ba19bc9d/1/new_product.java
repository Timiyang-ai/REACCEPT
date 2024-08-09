public void persistFile(long fileId, List<Long> blockIds) throws IOException {
    Map<Long, Long> blockIdToLockId;
    synchronized (mLock) {
      blockIdToLockId = mPersistingInProgressFiles.get(fileId);
      if (blockIdToLockId == null || !blockIdToLockId.keySet().equals(new HashSet<>(blockIds))) {
        throw new IOException("Not all the blocks of file " + fileId + " are locked");
      }
    }

    String dstPath = prepareUfsFilePath(fileId);
    // TODO(chaomin): should also propagate ancestor dirs permission to UFS.
    FileInfo fileInfo = mBlockWorker.getFileInfo(fileId);
    Permission perm = new Permission(fileInfo.getOwner(), fileInfo.getGroup(),
        (short) fileInfo.getMode());
    OutputStream outputStream = mUfs.create(dstPath, new CreateOptions().setPermission(perm));
    final WritableByteChannel outputChannel = Channels.newChannel(outputStream);

    List<Throwable> errors = new ArrayList<>();
    try {
      for (long blockId : blockIds) {
        long lockId = blockIdToLockId.get(blockId);

        if (Configuration.getBoolean(Constants.WORKER_FILE_PERSIST_RATE_LIMIT_ENABLED)) {
          BlockMeta blockMeta =
              mBlockWorker.getBlockMeta(Sessions.CHECKPOINT_SESSION_ID, blockId, lockId);
          getRateLimiter().acquire((int) blockMeta.getBlockSize());
        }

        // obtain block reader
        BlockReader reader =
            mBlockWorker.readBlockRemote(Sessions.CHECKPOINT_SESSION_ID, blockId, lockId);

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
          mBlockWorker.unlockBlock(lockId);
        } catch (BlockDoesNotExistException e) {
          errors.add(e);
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