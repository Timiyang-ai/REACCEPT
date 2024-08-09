@Test
  public void clearPersistedFiles() throws Exception {
    writeFileWithBlocks(1L, ImmutableList.of(2L, 3L));
    mManager.clearPersistedFiles(ImmutableList.of(1L));
    FileDataManager.PersistedFilesInfo info = mManager.getPersistedUfsFingerprints();
    assertEquals(Collections.emptyList(), info.idList());
  }