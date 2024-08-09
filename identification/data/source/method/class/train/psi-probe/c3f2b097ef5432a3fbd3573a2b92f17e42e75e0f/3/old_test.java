  @Test
  public void toLongTest() {
    Assertions.assertEquals(5L, Utils.toLong("garbage", 5L));
    Assertions.assertEquals(3L, Utils.toLong("3", 5L));
    Assertions.assertEquals(5L, Utils.toLong("3 3", 5L));
    Assertions.assertEquals(5L, Utils.toLong((String) null, 5L));
  }