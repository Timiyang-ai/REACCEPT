@Test
  public final void get() {
    ok(new Get());
    ok(new Get(MainOptions.CHOP));
    no(new Get(NAME2));
  }