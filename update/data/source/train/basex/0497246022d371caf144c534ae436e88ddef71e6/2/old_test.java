@Test public void get() throws Exception {
    // GET1 - just send a GET request
    try(QueryProcessor qp = new QueryProcessor(_HTTP_SEND_REQUEST.args(
        " <http:request method='get' href='" + REST_ROOT + "'/>"), ctx)) {
      final Value v = qp.value();
      checkResponse(v, 2, HttpURLConnection.HTTP_OK);

      assertEquals(NodeType.DOC, v.itemAt(1).type);
    }

    // GET2 - with override-media-type='text/plain'
    try(QueryProcessor qp = new QueryProcessor(_HTTP_SEND_REQUEST.args(
        " <http:request method='get' override-media-type='text/plain'/>", REST_ROOT), ctx)) {
      final Value v = qp.value();
      checkResponse(v, 2, HttpURLConnection.HTTP_OK);

      assertEquals(AtomType.STR, v.itemAt(1).type);
    }

    // Get3 - with status-only='true'
    try(QueryProcessor qp = new QueryProcessor(_HTTP_SEND_REQUEST.args(
        " <http:request method='get' status-only='true'/>", REST_ROOT), ctx)) {
      checkResponse(qp.value(), 1, HttpURLConnection.HTTP_OK);
    }
  }