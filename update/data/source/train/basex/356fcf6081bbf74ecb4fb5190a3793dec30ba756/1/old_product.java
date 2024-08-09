private static void error(final String query, final QNm error) {
    final String q = "import module namespace geo='http://expath.org/ns/geo'; " +
        "declare namespace gml='http://www.opengis.net/gml';" + query;

    final QueryProcessor qp = new QueryProcessor(q, context);
    try {
      final String res = qp.execute().toString().replaceAll("(\\r|\\n) *", "");
      fail("Query did not fail:\n" + query + "\n[E] " +
          error + "...\n[F] " + res);
    } catch(final QueryException ex) {
      if(!ex.qname().eq(error))
        fail("Wrong error code:\n[E] " + error + "\n[F] " + ex.qname());
    } finally {
      qp.close();
    }
  }