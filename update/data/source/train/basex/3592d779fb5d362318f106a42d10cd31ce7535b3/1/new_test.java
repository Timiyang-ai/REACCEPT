@Test
  public void run() {
    set(MainOptions.TEXTINDEX, false);
    set(MainOptions.ATTRINDEX, false);
    set(MainOptions.AUTOFLUSH, false);
    set(MainOptions.INTPARSE, true);

    // create test database
    execute(new CreateDB(NAME));

    // add documents
    for(int i = 0; i < NQUERIES; i++) execute(new Add(i + IO.XMLSUFFIX, "<a/>"));
    execute(new Flush());

    // replace documents with same content
    for(int i = 0; i < NQUERIES; i++) execute(new Replace(i + IO.XMLSUFFIX, "<a/>"));

    // Drop database
    execute(new DropDB(NAME));
  }