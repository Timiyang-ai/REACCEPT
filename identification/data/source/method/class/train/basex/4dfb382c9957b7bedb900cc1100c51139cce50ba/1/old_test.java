@Test
  public void parse() throws Exception {
    context.prop.set(Prop.STRIPNS, true);
    context.prop.set(Prop.SERIALIZER, "indent=no");

    final String doc = "<e xmlns='A'><b:f xmlns:b='B'/></e>";
    for(final boolean b : new boolean[] { false, true }) {
      context.prop.set(Prop.INTPARSE, b);
      new CreateDB(NAME, doc).execute(context);
      String result = new XQuery(".").execute(context);
      assertEquals("<e><f/></e>", result);
      result = new XQuery("e/f").execute(context);
      assertEquals("<f/>", result);
    }
  }