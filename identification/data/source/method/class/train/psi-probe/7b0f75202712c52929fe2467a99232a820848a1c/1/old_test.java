@Test
  public void leftPadTest() {
    Assert.assertEquals("0005", Utils.leftPad("5", 4, "0"));
    Assert.assertEquals("5", Utils.leftPad("5", 1, "0"));
  }