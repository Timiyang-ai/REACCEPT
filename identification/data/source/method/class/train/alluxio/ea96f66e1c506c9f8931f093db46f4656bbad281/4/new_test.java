  @Test
  public void getNumLocations() {
    mInfo.addWorker(1, "MEM");
    mInfo.addWorker(2, "MEM");
    mInfo.addWorker(3, "HDD");
    assertEquals(3, mInfo.getNumLocations());
  }