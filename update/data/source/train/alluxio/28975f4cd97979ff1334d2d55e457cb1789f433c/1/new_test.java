@Test
  public void persistFile() throws Exception {
    long fileId = 1;
    List<Long> blockIds = Lists.newArrayList(1L, 2L);

    writeFileWithBlocks(fileId, blockIds);

    // verify file persisted
    FileDataManager.PersistedFilesInfo info = mManager.getPersistedFileInfos();
    assertEquals(Arrays.asList(fileId), info.idList());

    // verify fastCopy called twice, once per block
    assertEquals(2, mCopyCounter.get());

    // verify the file is not needed for another persistence
    assertFalse(mManager.needPersistence(fileId));
  }