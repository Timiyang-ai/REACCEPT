  @Test(expectedExceptions = IllegalArgumentException.class)
  public void maximumSize_negative() {
    Caffeine.newBuilder().maximumSize(-1);
  }