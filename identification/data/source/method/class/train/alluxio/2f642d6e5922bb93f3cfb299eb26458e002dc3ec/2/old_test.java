  @Test
  public void defaults() {
    WaitForOptions options = WaitForOptions.defaults();
    assertEquals(WaitForOptions.DEFAULT_INTERVAL, options.getInterval());
    assertEquals(WaitForOptions.NEVER, options.getTimeoutMs());
  }