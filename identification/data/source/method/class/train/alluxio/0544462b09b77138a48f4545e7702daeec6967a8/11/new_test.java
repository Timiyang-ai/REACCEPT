  @Test
  public void getDirView() {
    for (int i = 0; i < TieredBlockStoreTestUtils.TIER_PATH[TEST_TIER_LEVEL].length; i++) {
      Assert.assertEquals(i, mTestTierView.getDirView(i).getDirViewIndex());
    }
  }