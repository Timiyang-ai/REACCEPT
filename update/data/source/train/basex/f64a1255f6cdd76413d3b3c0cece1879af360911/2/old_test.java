@Test
  public final void get() {
    ok(new Get(CmdSet.CHOP));
    no(new Get(NAME2));
  }