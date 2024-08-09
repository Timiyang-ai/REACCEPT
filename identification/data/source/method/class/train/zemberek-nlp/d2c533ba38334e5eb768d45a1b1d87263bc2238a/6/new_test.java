  @Test
  public void insertFromRightTest() {
    final String s = "0123456789";
    assertEquals(insertFromRight(s, 0, "-"), "0123456789");
    assertEquals(insertFromRight(s, 1, "-"), "0-1-2-3-4-5-6-7-8-9");
    assertEquals(insertFromRight(s, 2, "-"), "01-23-45-67-89");
    assertEquals(insertFromRight(s, 3, "-"), "0-123-456-789");
    assertEquals(insertFromRight(s, 5, "-"), "01234-56789");
    assertEquals(insertFromRight(s, 6, "-"), "0123-456789");
    assertEquals(insertFromRight(s, 9, "-"), "0-123456789");
    assertEquals(insertFromRight(s, 10, "-"), "0123456789");
    assertEquals(insertFromRight(s, 12, "-"), "0123456789");
    assertEquals(insertFromRight(s, 2, "--"), "01--23--45--67--89");
    assertEquals(insertFromRight(s, 3, "--"), "0--123--456--789");
  }