  @Test
  public void isInTier() {
    mInfo.addWorker(1, "HDD");
    assertTrue(mInfo.isInTier("HDD"));
  }