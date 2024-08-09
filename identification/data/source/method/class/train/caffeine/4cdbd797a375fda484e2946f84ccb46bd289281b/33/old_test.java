  @Test(expectedExceptions = IllegalArgumentException.class)
  public void initialCapacity_negative() {
    Caffeine.newBuilder().initialCapacity(-1);
  }