  @Test
  public void getDirViews() {
    Assert.assertEquals(TieredBlockStoreTestUtils.TIER_PATH[TEST_TIER_LEVEL].length, mTestTierView
        .getDirViews().size());
  }