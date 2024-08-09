  @Test
  public void isFullyInMemory() throws Exception {
    // add nested file
    mFileSystemMaster.createFile(NESTED_FILE_URI, mNestedFileContext);
    // add in-memory block
    long blockId = mFileSystemMaster.getNewBlockIdForFile(NESTED_FILE_URI);
    mBlockMaster.commitBlock(mWorkerId1, Constants.KB, "MEM", "MEM", blockId, Constants.KB);
    // add SSD block
    blockId = mFileSystemMaster.getNewBlockIdForFile(NESTED_FILE_URI);
    mBlockMaster.commitBlock(mWorkerId1, Constants.KB, "SSD", "SSD", blockId, Constants.KB);
    mFileSystemMaster.completeFile(NESTED_FILE_URI, CompleteFileContext.defaults());

    // Create 2 files in memory.
    createFileWithSingleBlock(ROOT_FILE_URI);
    AlluxioURI nestedMemUri = NESTED_URI.join("mem_file");
    createFileWithSingleBlock(nestedMemUri);
    assertEquals(2, mFileSystemMaster.getInMemoryFiles().size());
    assertTrue(mFileSystemMaster.getInMemoryFiles().contains(ROOT_FILE_URI));
    assertTrue(mFileSystemMaster.getInMemoryFiles().contains(nestedMemUri));
  }