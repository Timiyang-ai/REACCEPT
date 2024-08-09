  @Test
  public void acquireAccess() throws Exception {
    UnderFileSystemBlockStore blockStore =
        new UnderFileSystemBlockStore(mAlluxioBlockStore, mUfsManager);
    for (int i = 0; i < 5; i++) {
      assertTrue(blockStore.acquireAccess(i + 1, BLOCK_ID, mOpenUfsBlockOptions));
    }

    assertFalse(blockStore.acquireAccess(6, BLOCK_ID, mOpenUfsBlockOptions));
  }