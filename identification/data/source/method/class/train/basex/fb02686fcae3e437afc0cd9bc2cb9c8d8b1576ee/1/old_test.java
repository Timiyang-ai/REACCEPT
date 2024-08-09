@Test
  public void parse() {
    set(MainOptions.STRIPNS, true);
    set(MainOptions.SERIALIZER, SerializerOptions.get(false));

    final String doc = "<e xmlns='A'><b:f xmlns:b='B'/></e>";
    for(final boolean b : new boolean[] { false, true }) {
      set(MainOptions.INTPARSE, b);
      execute(new CreateDB(NAME, doc));
      assertEquals("<e><f/></e>", query("."));
      assertEquals("<f/>", query("e/f"));
    }
  }