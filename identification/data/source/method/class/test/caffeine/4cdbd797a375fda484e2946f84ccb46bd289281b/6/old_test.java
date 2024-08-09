  @Test(expectedExceptions = IllegalStateException.class)
  public void weakValues_twice() {
    Caffeine.newBuilder().weakValues().weakValues();
  }