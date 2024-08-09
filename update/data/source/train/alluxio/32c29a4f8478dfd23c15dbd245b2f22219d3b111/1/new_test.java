@Test
  public void formatWorkerTieredStoreAlias() throws Exception {
    Assert.assertEquals(PropertyKey.WORKER_TIERED_STORE_LEVEL0_ALIAS,
        PropertyKeyFormat.WORKER_TIERED_STORE_LEVEL_ALIAS_FORMAT.format(0));
    Assert.assertEquals(PropertyKey.WORKER_TIERED_STORE_LEVEL1_ALIAS,
        PropertyKeyFormat.WORKER_TIERED_STORE_LEVEL_ALIAS_FORMAT.format(1));
    Assert.assertEquals(PropertyKey.WORKER_TIERED_STORE_LEVEL2_ALIAS,
        PropertyKeyFormat.WORKER_TIERED_STORE_LEVEL_ALIAS_FORMAT.format(2));
  }