@Test
  public void parse() throws Exception {
    context.options.set(Options.STRIPNS, true);
    context.options.set(Options.SERIALIZER, "indent=no");

    final String doc = "<e xmlns='A'><b:f xmlns:b='B'/></e>";
    for(final boolean b : new boolean[] { false, true }) {
      context.options.set(Options.INTPARSE, b);
      new CreateDB(NAME, doc).execute(context);
      String result = new XQuery(".").execute(context);
      assertEquals("<e><f/></e>", result);
      result = new XQuery("e/f").execute(context);
      assertEquals("<f/>", result);
    }
  }