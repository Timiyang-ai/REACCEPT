@Test public void testTrim() {
    assertEquals("", trimSpacesBoth(""));
    assertEquals("", trimSpacesBoth("    "));
    assertEquals("x", trimSpacesBoth("   x  "));
    assertEquals("x", trimSpacesBoth("   x "));
    assertEquals("x y", trimSpacesBoth("   x y "));
    assertEquals("x", trimSpacesBoth("   x"));
    assertEquals("x", trimSpacesBoth("x"));
  }