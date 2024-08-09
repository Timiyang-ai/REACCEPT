  @Test
  public void linearToLogTest() {
    LogMath.LinearToLogConverter converter = new LogMath.LinearToLogConverter(Math.E);
    Assert.assertEquals(Math.log(2), converter.convert(2), 0.00000001d);
    converter = new LogMath.LinearToLogConverter(10d);
    Assert.assertEquals(Math.log10(2), converter.convert(2), 0.00000001d);

  }