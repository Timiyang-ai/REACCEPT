@Test
  public void error() throws Exception {
    try(QueryProcessor qp = new QueryProcessor(_HTTP_SEND_REQUEST.args(
        "<http:request method='get'/>", RESTURL + "unknown") + "[1]/@status/data()", ctx)) {
      assertEquals("404", qp.value().serialize().toString());
    }
  }