@Test
public final void replace() {
  // database must be opened to rename paths
  no(new Replace(FILE, "xxx"));
  ok(new CreateDB(NAME, FILE));
  ok(new Replace(FN, "<a/>"));
  ok(new Replace(FN, "<a/>"));
  no(new Replace(FN, ""));
  // create binary file
  ok(new XQuery("db:store('" + NAME + "', 'a', 'a')"));
  // Replace binary file content
  ok(new Replace("a", "<b/>"));
  assertTrue(!ok(new XQuery("db:open('" + NAME + "')")).isEmpty());
  // Verify binary file content after replacement
  ok(new XQuery("db:retrieve('" + NAME + "', 'a')"));
  // a failing replace should not remove existing documents
  no(new Replace(FN, "<a>"));
  assertTrue(!ok(new XQuery("doc('" + NAME + "')")).isEmpty());
}