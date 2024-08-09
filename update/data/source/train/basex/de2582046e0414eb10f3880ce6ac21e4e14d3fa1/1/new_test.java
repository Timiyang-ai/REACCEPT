@Test
  public final void replace() {
    // database must be opened to rename paths
    no(new Replace(FILE, "xxx"));
    ok(new CreateDB(NAME, FILE));
    ok(new Replace(FN, "<a/>"));
    ok(new Replace(FN, "<a/>"));
    no(new Replace(FN, ""));
    // a failing replace should not remove existing documents
    no(new Replace(FN, "<a>"));
    assertTrue(!ok(new XQuery("doc('" + NAME + "')")).isEmpty());
  }