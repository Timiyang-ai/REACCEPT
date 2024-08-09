  @Test
  public void leftTrimTest() {
    assertNull(leftTrim(null));
    assertEquals(leftTrim(""), "");
    assertEquals(leftTrim(" \t "), "");
    assertEquals(leftTrim(" 123"), "123");
    assertEquals(leftTrim("\t123"), "123");
    assertEquals(leftTrim("\n123"), "123");
    assertEquals(leftTrim("123"), "123");
    assertEquals(leftTrim(" \n  123"), "123");
    assertEquals(leftTrim("123 "), "123 ");
    assertEquals(leftTrim(" 3 123 "), "3 123 ");
  }