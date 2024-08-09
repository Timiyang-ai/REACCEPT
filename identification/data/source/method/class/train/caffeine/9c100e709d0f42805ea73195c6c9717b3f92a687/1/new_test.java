  @Test(expectedExceptions = NullPointerException.class)
  public void guardedScheduler_null() {
    Scheduler.guardedScheduler(null);
  }