  @Test
  public void getCapacityBytesOnTiers() {
    Map<String, Long> expectedCapacityBytesOnTiers = ImmutableMap.of("MEM", 5000L, "SSD", 60000L);
    Assert.assertEquals(expectedCapacityBytesOnTiers, mBlockStoreMeta.getCapacityBytesOnTiers());
  }