  @Test(expectedExceptions = IllegalStateException.class)
  public void weakKeys_twice() {
    Caffeine.newBuilder().weakKeys().weakKeys();
  }