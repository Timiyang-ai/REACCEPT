  @Test(expectedExceptions = IllegalArgumentException.class)
  public void refreshAfterWrite_negative() {
    Caffeine.newBuilder().refreshAfterWrite(-1, TimeUnit.MILLISECONDS);
  }