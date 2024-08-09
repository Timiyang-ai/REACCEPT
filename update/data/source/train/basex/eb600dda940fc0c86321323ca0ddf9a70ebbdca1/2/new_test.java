@Test
  public void put() throws Exception {
    try(QueryProcessor qp = new QueryProcessor(_HTTP_SEND_REQUEST.args(
        "<http:request method='put' status-only='true'>"
        + "<http:body media-type='text/xml'>" + BOOKS + "</http:body>"
        + "</http:request>", RESTURL), ctx)) {
      checkResponse(qp.value(), 1, HttpURLConnection.HTTP_CREATED);
    }
  }