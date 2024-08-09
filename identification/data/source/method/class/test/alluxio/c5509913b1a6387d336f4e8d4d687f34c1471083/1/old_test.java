  @Test
  public void getCapacityBytes() {
    Assert.assertEquals(TieredBlockStoreTestUtils.getDefaultTotalCapacityBytes(),
        mBlockStoreMeta.getCapacityBytes());
  }