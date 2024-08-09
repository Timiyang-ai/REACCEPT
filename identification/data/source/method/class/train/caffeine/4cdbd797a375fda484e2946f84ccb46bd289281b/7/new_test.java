  @Test(expectedExceptions = NullPointerException.class)
  public void ticker_null() {
    Caffeine.newBuilder().ticker(null);
  }