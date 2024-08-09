@Test
  public void run() throws Exception {
    context.prop.set(Prop.TEXTINDEX, false);
    context.prop.set(Prop.ATTRINDEX, false);
    context.prop.set(Prop.AUTOFLUSH, false);

    // create test database
    new CreateDB(NAME).execute(context);

    // replace nodes
    for(int i = 0; i < NQUERIES; i++) {
      new Add(i + IO.XMLSUFFIX, "<a/>").execute(context);
    }

    // replace nodes with same content
    for(int i = 0; i < NQUERIES; i++) {
      new Replace(i + IO.XMLSUFFIX, "<a/>").execute(context);
    }

    // Drop database
    new DropDB(NAME).execute(context);
  }