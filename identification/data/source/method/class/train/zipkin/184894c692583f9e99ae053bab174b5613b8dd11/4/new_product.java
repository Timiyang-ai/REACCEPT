@Override
  public ListenableFuture<List<List<Span>>> getTraces(final QueryRequest request) {
    // Over fetch on indexes as they don't return distinct (trace id, timestamp) rows.
    final int traceIndexFetchSize = request.limit * indexFetchMultiplier;
    ListenableFuture<Map<TraceIdUDT, Long>> traceIdToTimestamp = getTraceIdsByServiceNames(request);
    List<String> annotationKeys = CassandraUtil.annotationKeys(request);
    ListenableFuture<Collection<TraceIdUDT>> traceIds;
    if (annotationKeys.isEmpty()) {
      // Simplest case is when there is no annotation query. Limit is valid since there's no AND
      // query that could reduce the results returned to less than the limit.
      traceIds =
          Futures.transform(traceIdToTimestamp, CassandraUtil.traceIdsSortedByDescTimestamp());
    } else {
      // While a valid port of the scala cassandra span store (from zipkin 1.35), there is a fault.
      // each annotation key is an intersection, meaning we likely return < traceIndexFetchSize.
      List<ListenableFuture<Map<TraceIdUDT, Long>>> futureKeySetsToIntersect = new ArrayList<>();
      if (request.spanName != null) {
        futureKeySetsToIntersect.add(traceIdToTimestamp);
      }
      for (String annotationKey : annotationKeys) {
        futureKeySetsToIntersect
            .add(getTraceIdsByAnnotation(annotationKey, request.endTs, request.lookback, traceIndexFetchSize));
      }
      // We achieve the AND goal, by intersecting each of the key sets.
      traceIds =
          Futures.transform(allAsList(futureKeySetsToIntersect), CassandraUtil.intersectKeySets());
      // @xxx the sorting by timestamp desc is broken here^
    }
    return transform(traceIds, new AsyncFunction<Collection<TraceIdUDT>, List<List<Span>>>() {
      @Override
      public ListenableFuture<List<List<Span>>> apply(@Nullable Collection<TraceIdUDT> traceIds) {
        ImmutableSet<TraceIdUDT> set =
            ImmutableSet.copyOf(Iterators.limit(traceIds.iterator(), request.limit));
        return transform(getSpansByTraceIds(set, maxTraceCols),
            new Function<List<Span>, List<List<Span>>>() {
              @Override public List<List<Span>> apply(@Nullable List<Span> input) {
                return GroupByTraceId.apply(input, strictTraceId, true);
              }
            });
      }

      @Override public String toString() {
        return "getSpansByTraceIds";
      }
    });
  }