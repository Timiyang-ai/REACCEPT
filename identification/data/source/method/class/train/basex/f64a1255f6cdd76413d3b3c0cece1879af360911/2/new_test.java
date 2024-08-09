@Test
  public final void get() {
    ok(new Get(Prop.CHOP));
    no(new Get(NAME2));
  }