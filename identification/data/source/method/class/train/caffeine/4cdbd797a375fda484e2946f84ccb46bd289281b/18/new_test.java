  @Test(expectedExceptions = NullPointerException.class)
  public void executor_null() {
    Caffeine.newBuilder().executor(null);
  }