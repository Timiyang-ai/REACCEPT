@Test
  public void run() throws Exception {
    Connector c = getConnector();
    runTest(c, getUniqueNames(1)[0]);
  }