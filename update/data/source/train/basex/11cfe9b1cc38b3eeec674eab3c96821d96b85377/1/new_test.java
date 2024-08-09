@Test
  public void replace() throws Exception {
    new CreateDB(DBNAME, "<X><A>q</A><A>q</A></X>").execute(CONTEXT);
    final long size = CONTEXT.data().meta.dbfile(DataText.DATATXT).length();
    for(int n = 0; n < NQUERIES; n++) {
      final String qu =
          "for $a in //text() " +
          "let $d := math:random() " +
          "return replace node $a with $d";
      new XQuery(qu).execute(CONTEXT);
    }
    check(size);
  }