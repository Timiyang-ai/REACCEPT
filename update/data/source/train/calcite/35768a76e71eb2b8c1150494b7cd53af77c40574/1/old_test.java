@Test public void testTrim() {
    assertEquals("", trim(""));
    assertEquals("", trim("    "));
    assertEquals("x", trim("   x  "));
    assertEquals("x", trim("   x "));
    assertEquals("x y", trim("   x y "));
    assertEquals("x", trim("   x"));
    assertEquals("x", trim("x"));
  }