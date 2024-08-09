  @Test
  public void rightTrimTest() {
    assertNull(rightTrim(null));
    assertEquals(rightTrim(""), "");
    assertEquals(rightTrim(" \t"), "");
    assertEquals(rightTrim("aaa "), "aaa");
    assertEquals(rightTrim("aaa  \t "), "aaa");
    assertEquals(rightTrim("aaa\n "), "aaa");
    assertEquals(rightTrim("aaa"), "aaa");
    assertEquals(rightTrim(" 123 "), " 123");
    assertEquals(rightTrim(" 3 123 \t"), " 3 123");
  }