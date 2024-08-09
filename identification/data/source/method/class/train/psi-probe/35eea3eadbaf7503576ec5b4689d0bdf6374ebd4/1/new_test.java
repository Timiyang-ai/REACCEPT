  @Test
  public void toIntHexTest() {
    Assertions.assertEquals(5, Utils.toIntHex("garbage", 5));
    Assertions.assertEquals(3, Utils.toIntHex("3", 5));
    Assertions.assertEquals(3, Utils.toIntHex("#3", 5));
    Assertions.assertEquals(5, Utils.toIntHex("3 3", 5));
    Assertions.assertEquals(5, Utils.toIntHex((String) null, 5));
  }