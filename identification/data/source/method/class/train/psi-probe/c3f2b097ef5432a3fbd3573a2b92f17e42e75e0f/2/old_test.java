  @Disabled
  @Test
  public void toFloatTest() {
    Assertions.assertEquals(5.0f, Utils.toFloat("garbage", 5.0f), 0.0);
    Assertions.assertEquals(3.0f, Utils.toFloat("3", 5.0f), 0.0);
    Assertions.assertEquals(5.0f, Utils.toFloat("3 3", 5.0f), 0.0);
    Assertions.assertEquals(5.0f, Utils.toFloat((String) null, 5.0f), 0.0);
  }