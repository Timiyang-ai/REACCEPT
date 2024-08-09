@Test
  public final void replace() {
    // query to count number of documents
    final String count = "count(db:open('" + NAME + "'))";
    // database must be opened to replace resources
    no(new Replace(FILE, "xxx"));
    ok(new CreateDB(NAME, FILE));
    assertEquals("1", ok(new XQuery(count)));
    // replace existing document
    ok(new Replace(FN, "<a/>"));
    assertEquals("1", ok(new XQuery(count)));
    // replace existing document (again)
    ok(new Replace(FN, "<a/>"));
    assertEquals("1", ok(new XQuery(count)));
    // invalid content
    no(new Replace(FN, ""));
    assertEquals("1", ok(new XQuery(count)));
    // create and replace binary file
    ok(new XQuery("db:store('" + NAME + "', 'a', 'a')"));
    ok(new Replace("a", "<b/>"));
    assertFalse(ok(new XQuery("db:open('" + NAME + "')")).isEmpty());
    ok(new XQuery("db:retrieve('" + NAME + "', 'a')"));
    // a failing replace should not remove existing documents
    no(new Replace(FN, "<a>"));
    assertEquals("1", ok(new XQuery(count)));
  }