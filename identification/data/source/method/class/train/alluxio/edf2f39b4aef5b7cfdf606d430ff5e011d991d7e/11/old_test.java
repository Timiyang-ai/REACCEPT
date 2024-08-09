  @Test
  public void getStoreMeta() {
    mBlockWorker.getStoreMeta();
    mBlockWorker.getStoreMetaFull();
    verify(mBlockStore, times(2)).getBlockStoreMeta(); // 1 is called at metrics registration
    verify(mBlockStore).getBlockStoreMetaFull();
  }