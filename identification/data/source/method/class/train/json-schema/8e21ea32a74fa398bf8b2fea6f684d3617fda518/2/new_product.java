public QueryResult query() {
    JSONObject document = documentProvider.get();
    if (fragment.isEmpty()) {
      return new QueryResult(document, document);
    }
    String[] path = fragment.split("/");
    if ((path[0] == null) || !path[0].startsWith("#")) {
      throw new IllegalArgumentException("JSON pointers must start with a '#'");
    }
    try {
      JSONObject result = queryFrom(document);
      return new QueryResult(document, result);
    } catch (JSONPointerException e) {
      throw new SchemaException(e.getMessage());
    }
  }