@Test
  public final void set() {
    ok(new Set(Options.CHOP, false));
    ok(new Set(Options.CHOP, true));
    ok(new Set("chop", true));
    ok(new Set("runs", 1));
    no(new Set("runs", true));
    no(new Set(NAME2, NAME2));
  }