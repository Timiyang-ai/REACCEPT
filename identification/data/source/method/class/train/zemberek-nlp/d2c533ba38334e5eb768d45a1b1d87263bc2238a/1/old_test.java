  @Test
  public void insertFromLeftTest() {
    final String s = "0123456789";
    assertEquals(insertFromLeft(s, 0, "-"), "0123456789");
    assertEquals(insertFromLeft(s, 1, "-"), "0-1-2-3-4-5-6-7-8-9");
    assertEquals(insertFromLeft("ahmet", 1, " "), "a h m e t");
    assertEquals(insertFromLeft(s, 2, "-"), "01-23-45-67-89");
    assertEquals(insertFromLeft(s, 3, "-"), "012-345-678-9");
    assertEquals(insertFromLeft(s, 5, "-"), "01234-56789");
    assertEquals(insertFromLeft(s, 6, "-"), "012345-6789");
    assertEquals(insertFromLeft(s, 9, "-"), "012345678-9");
    assertEquals(insertFromLeft(s, 10, "-"), "0123456789");
    assertEquals(insertFromLeft(s, 12, "-"), "0123456789");
    assertEquals(insertFromLeft(s, 2, "--"), "01--23--45--67--89");
  }