  @Test
  public void getBlockStoreMeta() {
    BlockStoreMeta meta = mMetaManager.getBlockStoreMeta();
    Assert.assertNotNull(meta);

    // Assert the capacities are at alias level [MEM: 1000][SSD: 0][HDD: 8000]
    Map<String, Long> exceptedCapacityBytesOnTiers = ImmutableMap.of("MEM", 1000L, "HDD", 8000L);
    Map<String, Long> exceptedUsedBytesOnTiers = ImmutableMap.of("MEM", 0L, "HDD", 0L);
    assertEquals(exceptedCapacityBytesOnTiers, meta.getCapacityBytesOnTiers());
    assertEquals(exceptedUsedBytesOnTiers, meta.getUsedBytesOnTiers());
  }