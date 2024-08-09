public void createBlockRemote(long sessionId, long blockId, String tierAlias, long initialBytes)
      throws BlockAlreadyExistsException, WorkerOutOfSpaceException, IOException {
    BlockStoreLocation loc = BlockStoreLocation.anyDirInTier(tierAlias);
    TempBlockMeta createdBlock = mBlockStore.createBlockMeta(sessionId, blockId, loc, initialBytes);
    String blockPath = createdBlock.getPath();
    FileUtils.createBlockPath(blockPath);
    FileUtils.createFile(blockPath);
    FileUtils.changeLocalFileToFullPermission(blockPath);
    LOG.info("Created new file block, block path: {}", blockPath);
  }