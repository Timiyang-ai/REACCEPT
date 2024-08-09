  @Test
  public void repeatTest() {
    assertEquals(repeat('c', -1), "");
    assertEquals(repeat('c', 3), "ccc");
    assertEquals(repeat('c', 1), "c");
    assertEquals(repeat('c', 0), "");

    assertNull(repeat(null, 1));
    assertEquals(repeat("ab", -1), "");
    assertEquals(repeat("ab", 3), "ababab");
    assertEquals(repeat("ab", 1), "ab");
    assertEquals(repeat("ab", 0), "");
  }