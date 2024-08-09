public QueryResult query() {
    JSONObject document = documentProvider.get();
    if (fragment.isEmpty()) {
      return new QueryResult(document, document);
    }
    String[] path = fragment.split("/");
    if (!"#".equals(path[0])) {
      throw new IllegalArgumentException("JSON pointers must start with a '#'");
    }
    Object current = document;
    for (int i = 1; i < path.length; ++i) {
      String segment = unescape(path[i]);
      if (current instanceof JSONObject) {
        if (!((JSONObject) current).has(segment)) {
          throw new SchemaException(String.format(
              "failed to resolve JSON pointer [%s]. Segment [%s] not found in %s", fragment,
              segment, document.toString()));
        }
        current = ((JSONObject) current).get(segment);
      } else if (current instanceof JSONArray) {
        current = ((JSONArray) current).get(Integer.parseInt(segment));
      }
    }
    return new QueryResult(document, (JSONObject) current);
  }