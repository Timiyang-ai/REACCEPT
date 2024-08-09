  @Test
  public void releaseAccess() throws Exception {
    UnderFileSystemBlockStore blockStore =
        new UnderFileSystemBlockStore(mAlluxioBlockStore, mUfsManager);
    for (int i = 0; i < 5; i++) {
      assertTrue(blockStore.acquireAccess(i + 1, BLOCK_ID, mOpenUfsBlockOptions));
      blockStore.releaseAccess(i + 1, BLOCK_ID);
    }

    assertTrue(blockStore.acquireAccess(6, BLOCK_ID, mOpenUfsBlockOptions));
  }