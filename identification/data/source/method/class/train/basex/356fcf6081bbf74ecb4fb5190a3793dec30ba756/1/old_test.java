@Test
  public void error() throws QueryException {
    final QueryProcessor qp = new QueryProcessor(_HTTP_SEND_REQUEST.args(
        "<http:request method='get'/>", RESTURL + "unknown") + "[1]/@status/data()", ctx);
    assertEquals("404", qp.execute().toString());
    qp.close();
  }