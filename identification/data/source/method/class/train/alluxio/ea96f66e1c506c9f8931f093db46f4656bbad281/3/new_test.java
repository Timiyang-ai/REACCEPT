  @Test
  public void removeWorker() {
    mInfo.addWorker(1, "MEM");
    mInfo.removeWorker(1);
    assertEquals(0, mInfo.getWorkers().size());
  }