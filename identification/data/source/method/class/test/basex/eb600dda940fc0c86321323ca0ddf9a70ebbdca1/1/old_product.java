private static void error(final String query, final QNm error, final QueryError qe) {
    final String q = "import module namespace geo='http://expath.org/ns/geo'; " +
        "declare namespace gml='http://www.opengis.net/gml';" + query;

    try(final QueryProcessor qp = new QueryProcessor(q, context)) {
      final String res = qp.value().serialize().toString().replaceAll("(\\r|\\n) *", "");
      fail("Query did not fail:\n" + query + "\n[E] " + error + "...\n[F] " + res);
    } catch(final QueryException ex) {
      final QueryError qerr = ex.error();
      if(qe != null && qerr != null && qe != qerr) {
        Util.stack(ex);
        final StringBuilder sb = new StringBuilder("Wrong error code:\n[E] ");
        fail(sb.append(qe.name()).append("\n[F] ").append(qerr.name()).toString());
      } else if(!ex.qname().eq(error)) {
        Util.stack(ex);
        final StringBuilder sb = new StringBuilder("Wrong error code:\n[E] ");
        fail(sb.append(error).append("\n[F] ").append(ex.qname()).toString());
      }
    } catch(final Exception ex) {
      Util.stack(ex);
      fail(ex.toString());
    }
  }