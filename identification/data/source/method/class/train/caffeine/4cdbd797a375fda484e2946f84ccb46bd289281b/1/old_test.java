  @Test(expectedExceptions = NullPointerException.class)
  public void recordStats_null() {
    Caffeine.newBuilder().recordStats(null);
  }