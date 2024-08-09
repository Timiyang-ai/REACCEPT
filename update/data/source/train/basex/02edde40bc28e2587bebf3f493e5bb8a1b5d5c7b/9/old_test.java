@Test
  public final void get() {
    ok(new Get());
    ok(new Get(Options.CHOP));
    no(new Get(NAME2));
  }