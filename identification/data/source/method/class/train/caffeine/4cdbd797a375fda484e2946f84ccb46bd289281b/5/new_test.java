  @Test(expectedExceptions = IllegalArgumentException.class)
  public void expireAfterWrite_negative() {
    Caffeine.newBuilder().expireAfterWrite(-1, TimeUnit.MILLISECONDS);
  }