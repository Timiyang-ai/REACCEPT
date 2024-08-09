  @Test(expectedExceptions = IllegalArgumentException.class)
  public void maximumWeight_negative() {
    Caffeine.newBuilder().maximumWeight(-1);
  }