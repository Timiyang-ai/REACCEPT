  @Test
  public void addWorker() {
    mInfo.addWorker(1, "MEM");
    assertTrue(mInfo.getWorkers().contains(1L));
  }