  @Test
  public void resolve() {
    String[] paths = new String[] {"", "foo", "bar/baz"};
    for (String p1 : paths) {
      for (String p2 : paths) {
        ForwardRelativePath naive =
            ForwardRelativePath.of(p1 + (!p1.isEmpty() && !p2.isEmpty() ? "/" : "") + p2);
        ForwardRelativePath fast = ForwardRelativePath.of(p1).resolve(ForwardRelativePath.of(p2));
        assertEquals(naive, fast);
      }
    }
  }