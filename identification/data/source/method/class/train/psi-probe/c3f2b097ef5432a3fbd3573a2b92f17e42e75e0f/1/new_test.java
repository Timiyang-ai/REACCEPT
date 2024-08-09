  @Test
  public void toIntTest() {
    Assertions.assertEquals(5, Utils.toInt("garbage", 5));
    Assertions.assertEquals(3, Utils.toInt("3", 5));
    Assertions.assertEquals(5, Utils.toInt("3 3", 5));
    Assertions.assertEquals(5, Utils.toInt((String) null, 5));
  }