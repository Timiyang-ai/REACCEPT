@Test
public final void replace() {
  // database must be opened to rename paths
  no(new Replace(FILE, "xxx"));
  ok(new CreateDB(NAME, FILE));
  ok(new Replace(FN, "<a/>"));
  ok(new Replace(FN, "<a/>"));
  no(new Replace(FN, ""));
  // create binary file correctly using db:store
  ok(new XQuery("db:store('" + NAME + "', 'a', 'a')"));
  ok(new Replace("a", "<b/>"));
  assertTrue(!ok(new XQuery("db:open('" + NAME + "')")).isEmpty());
  ok(new XQuery("db:retrieve('" + NAME + "', 'a')"));
  // a failing replace should not remove existing documents
  no(new Replace(FN, "<a>"));
  assertTrue(!ok(new XQuery("doc('" + NAME + "')")).isEmpty());

  // Correcting the method to use db:store instead of the incorrect db:put
  // for binary file handling, as indicated by the failure message.
  ok(new XQuery("db:store('" + NAME + "', 'a', 'a')"));
  ok(new Replace("a", "<b/>"));
  assertTrue(!ok(new XQuery("db:open('" + NAME + "')")).isEmpty());
  ok(new XQuery("db:retrieve('" + NAME + "', 'a')"));
}