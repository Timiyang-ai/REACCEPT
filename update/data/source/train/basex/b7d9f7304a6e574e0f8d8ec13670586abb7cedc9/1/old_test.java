@Test
  public void run() throws Exception {
    CONTEXT.prop.set(Prop.TEXTINDEX, false);
    CONTEXT.prop.set(Prop.ATTRINDEX, false);
    CONTEXT.prop.set(Prop.AUTOFLUSH, false);

    // create test database
    new CreateDB(NAME).execute(CONTEXT);

    // replace nodes
    for(int i = 0; i < NQUERIES; i++) {
      new Add(i + IO.XMLSUFFIX, "<a/>").execute(CONTEXT);
    }

    // replace nodes with same content
    for(int i = 0; i < NQUERIES; i++) {
      new Replace(i + IO.XMLSUFFIX, "<a/>").execute(CONTEXT);
    }

    // Drop database
    new DropDB(NAME).execute(CONTEXT);
  }