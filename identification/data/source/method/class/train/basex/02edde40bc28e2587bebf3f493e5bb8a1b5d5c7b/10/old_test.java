@Test
  public void run() throws Exception {
    context.options.set(Options.TEXTINDEX, false);
    context.options.set(Options.ATTRINDEX, false);
    context.options.set(Options.AUTOFLUSH, false);

    // create test database
    new CreateDB(NAME, "<X>" +
        "<A>x.xxxxxxxxxxxxxxxxxx</A>" +
        "<A>x.xxxxxxxxxxxxxxxxxx</A></X>").execute(context);

    //final long len1 = CONTEXT.data().meta.dbfile(DataText.DATATXT).length();

    // replace texts with random doubles
    final Random rnd = new Random();
    for(int i = 0; i < NQUERIES; i++) {
      final double d = rnd.nextDouble();
      final String qu = "for $a in //A return replace node $a/text() with " + d;
      new XQuery(qu).execute(context);
    }

    // perform final, flushed replacement
    new Flush().execute(context);

    //final long len2 = CONTEXT.data().meta.dbfile(DataText.DATATXT).length();
    //assertEquals(len1, len2);

    // Drop database
    new DropDB(NAME).execute(context);
  }