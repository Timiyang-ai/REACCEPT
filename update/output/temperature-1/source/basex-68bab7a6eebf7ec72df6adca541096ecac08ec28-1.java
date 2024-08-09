@Test
public final void replace() {
    // database must be opened to rename paths
    no(new Replace(FILE, "xxx"));
    ok(new CreateDB(NAME, FILE));
    ok(new Replace(FN, "<a/>"));
    ok(new Replace(FN, "<a/>"));
    no(new Replace(FN, ""));
    
    // adapt to the production change: create binary file and test replacement
    ok(new XQuery("db:store('" + NAME + "', 'a', 'a')"));
    ok(new Replace("a", "<b/>"));
    assertTrue(!ok(new XQuery("db:open('" + NAME + "')")).isEmpty());
    
    // Replacing test based on new binary handling. Adjusting method calls to reflect actual operations
    ok(new XQuery("db:retrieve('" + NAME + "', 'a')"));
    
    // a failing replace should not remove existing documents
    no(new Replace(FN, "<a>"));
    assertTrue(!ok(new XQuery("doc('" + NAME + "')")).isEmpty());
}