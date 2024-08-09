  @Test(expectedExceptions = IllegalStateException.class)
  public void softValues_twice() {
    Caffeine.newBuilder().softValues().softValues();
  }