@Test
  public void parse() throws Exception {
    context.options.set(MainOptions.STRIPNS, true);
    final SerializerOptions sopts = new SerializerOptions();
    sopts.set(SerializerOptions.INDENT, YesNo.NO);
    context.options.set(MainOptions.SERIALIZER, sopts);

    final String doc = "<e xmlns='A'><b:f xmlns:b='B'/></e>";
    for(final boolean b : new boolean[] { false, true }) {
      context.options.set(MainOptions.INTPARSE, b);
      new CreateDB(NAME, doc).execute(context);
      String result = new XQuery(".").execute(context);
      assertEquals("<e><f/></e>", result);
      result = new XQuery("e/f").execute(context);
      assertEquals("<f/>", result);
    }
  }