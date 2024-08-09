@Test
  public void run() throws Exception {
    AccumuloClient c = getAccumuloClient();
    runTest(c, getUniqueNames(1)[0]);
  }