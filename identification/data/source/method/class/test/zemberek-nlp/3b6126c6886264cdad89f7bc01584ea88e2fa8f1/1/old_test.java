  @Test
  public void logSumExactTest() throws IOException {
    double[] aLinear = new double[1000];
    double[] bLinear = new double[1000];

    int j = 0;
    for (int i = 0; i < bLinear.length; i++) {
      aLinear[j] = (double) i / 1000d;
      bLinear[j] = aLinear[j];
      j++;
    }

    for (double a : aLinear) {
      for (double b : bLinear) {
        double logSumExact = LogMath.logSum(Math.log(a), Math.log(b));
        Assert.assertEquals("a=" + a + " b=" + b + " lin=" + Math.log(a + b),
            logSumExact,
            LogMath.LOG_SUM.lookup(Math.log(a), Math.log(b)), 0.001);
      }
    }
  }