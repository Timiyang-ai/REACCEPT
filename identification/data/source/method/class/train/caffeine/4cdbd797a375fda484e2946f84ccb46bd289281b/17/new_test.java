  @Test(expectedExceptions = IllegalArgumentException.class)
  public void expireAfterAccess_negative() {
    Caffeine.newBuilder().expireAfterAccess(-1, TimeUnit.MILLISECONDS);
  }