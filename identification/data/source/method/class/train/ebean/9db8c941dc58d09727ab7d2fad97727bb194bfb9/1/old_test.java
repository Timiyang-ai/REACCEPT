  @Test
  public void count() throws Exception {

    assertEquals(SplitName.count("a"), 0);
    assertEquals(SplitName.count("a.b"), 1);
    assertEquals(SplitName.count("a.b.c"), 2);
    assertEquals(SplitName.count("a.b.c.foo"), 3);
  }