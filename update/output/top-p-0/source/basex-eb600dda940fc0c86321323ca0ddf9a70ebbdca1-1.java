@Test
public void error() throws Exception {
  // Using try-with-resources for automatic resource management
  try(final QueryProcessor qp = new QueryProcessor(_HTTP_SEND_REQUEST.args(
      "<http:request method='get'/>", RESTURL + "unknown") + "[1]/@status/data()", ctx)) {
    // The expected result is to receive a 404 status code, indicating that the resource is not found
    assertEquals("404", qp.value().serialize().toString().replaceAll("(\\r|\\n) *", ""));
  }
  // The try-with-resources block ensures that the QueryProcessor instance is automatically closed,
  // thus there's no need for a finally block to explicitly close the resource.
}