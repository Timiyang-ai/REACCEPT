@Test
  public void run() throws Exception {
    new Set(Prop.TEXTINDEX, false).execute(CONTEXT);
    new Set(Prop.ATTRINDEX, false).execute(CONTEXT);

    // Create test database
    new CreateDB(DB, "<X><A>1</A><A>1</A></X>").execute(CONTEXT);

    long len1 = CONTEXT.data().meta.dbfile(DataText.DATATXT).length();

    // deactivate flushing to speed up querying
    new Set(Prop.AUTOFLUSH, false).execute(CONTEXT);

    // replace texts with random doubles
    final Random rnd = new Random();
    for(int i = 0; i < NQUERIES; i++) {
      final double d = rnd.nextDouble();
      final String qu = "for $a in //A return replace node $a/text() with " + d;
      new XQuery(qu).execute(CONTEXT);
    }

    // perform final, flushed replacement
    new Set(Prop.AUTOFLUSH, true).execute(CONTEXT);

    long len2 = CONTEXT.data().meta.dbfile(DataText.DATATXT).length();
    assertEquals(len1, len2);

    // Drop database
    new DropDB(DB).execute(CONTEXT);
    CONTEXT.close();
  }