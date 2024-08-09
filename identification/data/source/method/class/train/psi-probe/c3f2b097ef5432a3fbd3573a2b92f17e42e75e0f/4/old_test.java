  @Test
  public void leftPadTest() {
    Assertions.assertEquals("0005", Utils.leftPad("5", 4, "0"));
    Assertions.assertEquals("5", Utils.leftPad("5", 1, "0"));
    Assertions.assertEquals("", Utils.leftPad(null, 4, "0"));
  }