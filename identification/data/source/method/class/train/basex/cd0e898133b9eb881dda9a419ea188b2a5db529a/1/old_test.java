@Test
  public void put() throws Exception {
    final QueryProcessor qp = new QueryProcessor("http:send-request("
        + "<http:request method='put' status-only='true'>"
        + "<http:body media-type='text/xml'>" + BOOKS + "</http:body>"
        + "</http:request>, '" + URL + "')", CONTEXT);
    checkResponse(qp.execute(), HttpURLConnection.HTTP_CREATED, 1);
    qp.close();
  }