  @Test
  public void wasLoggedTest() {
    String logEvent = "This is a test";
    LOG.info(logEvent);
    assertTrue(mLogger.wasLogged(logEvent));
  }