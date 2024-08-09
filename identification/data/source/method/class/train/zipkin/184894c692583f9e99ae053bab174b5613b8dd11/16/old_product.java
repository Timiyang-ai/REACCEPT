@Override
  public ListenableFuture<List<List<Span>>> getTraces(final QueryRequest request) {
    // Over fetch on indexes as they don't return distinct (trace id, timestamp) rows.
    final int traceIndexFetchSize = request.limit * indexFetchMultiplier;
    ListenableFuture<Map<BigInteger, Long>> traceIdToTimestamp = getTraceIdsByServiceNames(request);
    List<String> annotationKeys = CassandraUtil.annotationKeys(request);
    ListenableFuture<Collection<BigInteger>> traceIds;
    if (annotationKeys.isEmpty()) {
      // Simplest case is when there is no annotation query. Limit is valid since there's no AND
      // query that could reduce the results returned to less than the limit.
      traceIds =
          Futures.transform(traceIdToTimestamp, CassandraUtil.traceIdsSortedByDescTimestamp());
    } else {
      // While a valid port of the scala cassandra span store (from zipkin 1.35), there is a fault.
      // each annotation key is an intersection, meaning we likely return < traceIndexFetchSize.
      List<ListenableFuture<Map<BigInteger, Long>>> futureKeySetsToIntersect = new ArrayList<>();
      futureKeySetsToIntersect.add(traceIdToTimestamp);
      for (String annotationKey : annotationKeys) {
        futureKeySetsToIntersect
            .add(getTraceIdsByAnnotation(annotationKey, request.endTs, request.lookback, traceIndexFetchSize));
      }
      // We achieve the AND goal, by intersecting each of the key sets.
      traceIds =
          Futures.transform(allAsList(futureKeySetsToIntersect), CassandraUtil.intersectKeySets());
      // @xxx the sorting by timestamp desc is broken here^
    }
    return transform(traceIds, new AsyncFunction<Collection<BigInteger>, List<List<Span>>>() {
      @Override public ListenableFuture<List<List<Span>>> apply(Collection<BigInteger> traceIds) {
        ImmutableSet<BigInteger> set = ImmutableSet.copyOf(traceIds);
        set = ImmutableSet.copyOf(Iterators.limit(set.iterator(), request.limit));
        return transform(getSpansByTraceIds(set, maxTraceCols), AdjustTraces.INSTANCE);
      }

      @Override public String toString() {
        return "getSpansByTraceIds";
      }
    });
  }