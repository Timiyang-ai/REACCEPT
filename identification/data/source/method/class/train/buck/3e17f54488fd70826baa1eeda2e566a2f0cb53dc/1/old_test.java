  @Test
  public void startsWith() {
    assertTrue(ForwardRelativePath.of("").startsWith(ForwardRelativePath.of("")));
    assertTrue(ForwardRelativePath.of("ab").startsWith(ForwardRelativePath.of("")));
    assertTrue(ForwardRelativePath.of("ab/cd").startsWith(ForwardRelativePath.of("")));
    assertTrue(ForwardRelativePath.of("ab/cd").startsWith(ForwardRelativePath.of("ab")));
    assertTrue(ForwardRelativePath.of("ab/cd").startsWith(ForwardRelativePath.of("ab/cd")));

    assertFalse(ForwardRelativePath.of("").startsWith(ForwardRelativePath.of("ab")));
    assertFalse(ForwardRelativePath.of("").startsWith(ForwardRelativePath.of("ab/cd")));
    assertFalse(ForwardRelativePath.of("ab").startsWith(ForwardRelativePath.of("ab/cd")));

    assertFalse(ForwardRelativePath.of("ab").startsWith(ForwardRelativePath.of("a")));
  }