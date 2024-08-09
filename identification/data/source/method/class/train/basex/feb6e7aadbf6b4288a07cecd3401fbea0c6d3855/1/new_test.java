@Test
  public void module() {
    final QueryContext qc = new QueryContext(context);
    try {
      qc.parseLibrary(
          "module namespace m='foo'; declare function m:foo() { m:bar() }; ", "");
      fail("Unknown function 'm:bar()' was not detected.");
    } catch(final QueryException e) {
      assertSame(Err.FUNCUNKNOWN, e.err());
    }
  }