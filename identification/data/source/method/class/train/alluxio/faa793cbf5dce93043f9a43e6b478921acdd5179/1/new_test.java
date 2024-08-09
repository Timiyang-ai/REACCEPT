  @Test
  public void updateUsedBytes() {
    assertEquals(Constants.KB * 2L, mInfo.getUsedBytes());
    Map<String, Long> usedBytesOnTiers =
        ImmutableMap.of("MEM", Constants.KB * 2L, "SSD", (long) Constants.KB);
    mInfo.updateUsedBytes(usedBytesOnTiers);
    assertEquals(usedBytesOnTiers, mInfo.getUsedBytesOnTiers());
    assertEquals(Constants.KB * 3L, mInfo.getUsedBytes());
  }