  @Test
  public void getFreeBytesOnTiers() {
    assertEquals(ImmutableMap.of("MEM", Constants.KB * 2L, "SSD", Constants.KB * 2L),
        mInfo.getFreeBytesOnTiers());
  }