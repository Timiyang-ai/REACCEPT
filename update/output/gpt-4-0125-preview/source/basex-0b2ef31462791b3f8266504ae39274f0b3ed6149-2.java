@Test
public void addFixed() {
    final String dbName = "SandboxTest";
    final String xmlContent = "<root/>";
    final String docPath = "t1.xml";
    // Assuming the options need to be an empty map, we'll try to explicitly create one in the query if the function supports it.
    final String options = "map{}"; // Adjusted to use a map constructor if supported

    // Ensure the database is created or exists before adding documents
    query("if (not(db:exists('" + dbName + "'))) then db:create('" + dbName + "') else ()");

    // Attempt to add the document with an explicit XML node and options map
    String addQuery = String.format(
        "let $doc := document {%s}\n" +
        "return db:add('%s', $doc, '%s', %s)",
        xmlContent, dbName, docPath, options);

    // Execute the add query
    query(addQuery);

    // Verify the document was added by checking the existence of the document
    String verifyQuery = String.format("exists(db:open('%s', '%s'))", dbName, docPath);
    assertTrue("Document was not added successfully", query(verifyQuery).equals("true"));
}