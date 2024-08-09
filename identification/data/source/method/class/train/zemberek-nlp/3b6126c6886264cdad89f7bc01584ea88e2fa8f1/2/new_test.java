  @Test
  public void logSumTest() throws IOException {
    double[] aLinear = new double[1000];
    double[] bLinear = new double[1000];

    for (int i = 0; i < bLinear.length; i++) {
      aLinear[i] = (double) i / 1000d;
      bLinear[i] = aLinear[i];
    }

    LogMath.LogSumLookup logSumE = LogMath.LOG_SUM;

    for (double a : aLinear) {
      for (double b : bLinear) {
        double logSumExpected = Math.log(a + b);
        Assert.assertEquals(logSumExpected, logSumE.lookup(Math.log(a), Math.log(b)), 0.001);
      }
    }
  }