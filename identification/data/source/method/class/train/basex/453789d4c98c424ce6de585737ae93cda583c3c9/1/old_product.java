protected static String eval(final String query) throws QueryException, IOException {
    final ArrayOutput ao = new ArrayOutput();
    try(QueryProcessor qp = new QueryProcessor(query, BASEURI, context)) {
      qp.register(context);
      try(Serializer ser = qp.getSerializer(ao)) {
        qp.value().serialize(ser);
      } finally {
        qp.unregister(context);
      }
    }
    return ao.toString();
  }