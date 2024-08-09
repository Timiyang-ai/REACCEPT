@Override
  public Call<List<List<Span>>> getTraces(final QueryRequest request) {
    // Over fetch on indexes as they don't return distinct (trace id, timestamp) rows.
    final int traceIndexFetchSize = request.limit() * indexFetchMultiplier;
    ListenableFuture<Map<String, Long>> traceIdToTimestamp = getTraceIdsByServiceNames(request);
    List<String> annotationKeys = CassandraUtil.annotationKeys(request);
    ListenableFuture<Collection<String>> traceIds;
    if (annotationKeys.isEmpty()) {
      // Simplest case is when there is no annotation query. Limit is valid since there's no AND
      // query that could reduce the results returned to less than the limit.
      traceIds = Futures.transform(traceIdToTimestamp, CassandraUtil.traceIdsSortedByDescTimestamp());
    } else {
      // While a valid port of the scala cassandra span store (from zipkin 1.35), there is a fault.
      // each annotation key is an intersection, meaning we likely return < traceIndexFetchSize.
      List<ListenableFuture<Map<String, Long>>> futureKeySetsToIntersect = new ArrayList<>();
      if (request.spanName() != null) {
        futureKeySetsToIntersect.add(traceIdToTimestamp);
      }
      for (String annotationKey : annotationKeys) {
        futureKeySetsToIntersect
            .add(getTraceIdsByAnnotation(request, annotationKey, request.endTs(), traceIndexFetchSize));
      }
      // We achieve the AND goal, by intersecting each of the key sets.
      traceIds = Futures.transform(allAsList(futureKeySetsToIntersect), CassandraUtil.intersectKeySets());
      // @xxx the sorting by timestamp desc is broken here^
    }

    return new ListenableFutureCall<List<List<Span>>>() {
      @Override protected ListenableFuture<List<List<Span>>> newFuture() {
        return transformAsync(traceIds, new AsyncFunction<Collection<String>, List<List<Span>>>() {
          @Override
          public ListenableFuture<List<List<Span>>> apply(Collection<String> traceIds) {
            ImmutableSet<String> set =
              ImmutableSet.copyOf(Iterators.limit(traceIds.iterator(), request.limit()));

            return Futures.transform(
                    getSpansByTraceIds(set, maxTraceCols),
                    (List<Span> input) -> {

                List<List<Span>> traces = groupByTraceId(input, strictTraceId);
                // Due to tokenization of the trace ID, our matches are imprecise on Span.traceIdHigh
                for (Iterator<List<Span>> trace = traces.iterator(); trace.hasNext(); ) {
                  List<Span> next = trace.next();
                  if (next.get(0).traceId().length() > 16 && !request.test(next)) {
                    trace.remove();
                  }
                }
                return traces;
              });
          }

          @Override public String toString() {
            return "getSpansByTraceIds";
          }
        });
      }
    };
  }