  private List<Query> splitQuery(Query query, int numSplits) {
    List<Query> queries = new ArrayList<>();
    int offsetOfOriginal = query.getOffset();
    for (int i = 0; i < numSplits; i++) {
      Query.Builder q = Query.newBuilder();
      q.addKindBuilder().setName(KIND);
      // Making sub-queries unique (and not equal to the original query) by setting different
      // offsets.
      q.setOffset(++offsetOfOriginal);
      queries.add(q.build());
    }
    return queries;
  }