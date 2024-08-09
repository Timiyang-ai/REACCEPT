  @Test
  public void splitStringTest() {
    assertEquals("Unexpected result", new ArrayList<>(Arrays.asList("a", "b", "c")), Utils.splitString("a,b,c", ","));
    assertEquals("Empty string should return empty list", new ArrayList<>(), Utils.splitString("", ","));
    assertEquals("Empty segments should be ignored", new ArrayList<>(Arrays.asList("a", "b-extra", "c")),
        Utils.splitString(",a,,b-extra,c,,", ","));
  }