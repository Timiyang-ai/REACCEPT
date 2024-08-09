public void completeFile(AlluxioURI path, CompleteFileOptions options)
      throws BlockInfoException, FileDoesNotExistException, InvalidPathException,
      InvalidFileSizeException, FileAlreadyCompletedException, AccessControlException {
    MasterContext.getMasterSource().incCompleteFileOps(1);
    synchronized (mInodeTree) {
      checkPermission(FileSystemAction.WRITE, path, false);
      // Even readonly mount points should be able to complete a file, for UFS reads in CACHE mode.
      long opTimeMs = System.currentTimeMillis();
      Inode<?> inode = mInodeTree.getInodeByPath(path);
      long fileId = inode.getId();
      if (!inode.isFile()) {
        throw new FileDoesNotExistException(ExceptionMessage.PATH_MUST_BE_FILE.getMessage(path));
      }

      InodeFile fileInode = (InodeFile) inode;
      List<Long> blockIdList = fileInode.getBlockIds();
      List<BlockInfo> blockInfoList = mBlockMaster.getBlockInfoList(blockIdList);
      if (!fileInode.isPersisted() && blockInfoList.size() != blockIdList.size()) {
        throw new BlockInfoException("Cannot complete a file without all the blocks committed");
      }

      // Iterate over all file blocks committed to Alluxio, computing the length and verify that all
      // the blocks (except the last one) is the same size as the file block size.
      long inMemoryLength = 0;
      long fileBlockSize = fileInode.getBlockSizeBytes();
      for (int i = 0; i < blockInfoList.size(); i++) {
        BlockInfo blockInfo = blockInfoList.get(i);
        inMemoryLength += blockInfo.getLength();
        if (i < blockInfoList.size() - 1 && blockInfo.getLength() != fileBlockSize) {
          throw new BlockInfoException(
              "Block index " + i + " has a block size smaller than the file block size ("
                  + fileInode.getBlockSizeBytes() + ")");
        }
      }

      // If the file is persisted, its length is determined by UFS. Otherwise, its length is
      // determined by its memory footprint.
      long length = fileInode.isPersisted() ? options.getUfsLength() : inMemoryLength;

      completeFileInternal(fileInode.getBlockIds(), fileId, length, opTimeMs);
      CompleteFileEntry completeFileEntry = CompleteFileEntry.newBuilder()
          .addAllBlockIds(fileInode.getBlockIds())
          .setId(fileId)
          .setLength(length)
          .setOpTimeMs(opTimeMs)
          .build();
      writeJournalEntry(JournalEntry.newBuilder().setCompleteFile(completeFileEntry).build());
      flushJournal();
    }
  }