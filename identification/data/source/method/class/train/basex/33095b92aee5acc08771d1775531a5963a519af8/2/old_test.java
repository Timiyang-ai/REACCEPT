@Test
  public final void set() {
    ok(new Set(MainOptions.CHOP, false));
    ok(new Set(MainOptions.CHOP, true));
    ok(new Set("chop", true));
    ok(new Set("runs", 1));
    ok(new Set(MainOptions.ATTRTOKENIZE, ""));
    ok(new Set(MainOptions.ATTRTOKENIZE, "id"));
    no(new Set("runs", true));
    no(new Set(NAME2, NAME2));
  }