  @Ignore
  @Test
  public void test_deregisterShutdownHook() {

    Ebean.getDefaultServer();
    ShutdownManager.deregisterShutdownHook();
  }