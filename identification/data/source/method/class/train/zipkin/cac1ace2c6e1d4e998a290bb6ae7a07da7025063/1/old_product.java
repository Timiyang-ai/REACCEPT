@Override
  public ListenableFuture<List<List<Span>>> getTraces(final QueryRequest request) {
    // Over fetch on indexes as they don't return distinct (trace id, timestamp) rows.
    final int traceIndexFetchSize = request.limit * indexFetchMultiplier;
    ListenableFuture<Map<Long, Long>> traceIdToTimestamp;
    if (request.spanName != null) {
      traceIdToTimestamp = getTraceIdsBySpanName(request.serviceName, request.spanName,
          request.endTs * 1000, request.lookback * 1000, traceIndexFetchSize);
    } else if (request.serviceName != null) {
      traceIdToTimestamp = getTraceIdsByServiceNames(Collections.singletonList(request.serviceName),
          request.endTs * 1000, request.lookback * 1000, traceIndexFetchSize);
    } else {
      checkArgument(selectTraceIdsByServiceNames != null,
          "getTraces without serviceName requires Cassandra 2.2 or later");
      traceIdToTimestamp = transform(getServiceNames(),
          new AsyncFunction<List<String>, Map<Long, Long>>() {
            @Override
            public ListenableFuture<Map<Long, Long>> apply(@Nullable List<String> serviceNames) {
              return getTraceIdsByServiceNames(serviceNames,
                  request.endTs * 1000, request.lookback * 1000, traceIndexFetchSize);
            }
          });
    }

    List<String> annotationKeys = CassandraUtil.annotationKeys(request);

    ListenableFuture<Set<Long>> traceIds;
    if (annotationKeys.isEmpty()) {
      // Simplest case is when there is no annotation query. Limit is valid since there's no AND
      // query that could reduce the results returned to less than the limit.
      traceIds = Futures.transform(traceIdToTimestamp, CassandraUtil.keyset());
    } else {
      // While a valid port of the scala cassandra span store (from zipkin 1.35), there is a fault.
      // each annotation key is an intersection, meaning we likely return < traceIndexFetchSize.
      List<ListenableFuture<Map<Long, Long>>> futureKeySetsToIntersect = new ArrayList<>();
      if (request.spanName != null) {
        futureKeySetsToIntersect.add(traceIdToTimestamp);
      }
      for (String annotationKey : annotationKeys) {
        futureKeySetsToIntersect.add(getTraceIdsByAnnotation(annotationKey,
            request.endTs * 1000, request.lookback * 1000, traceIndexFetchSize));
      }
      // We achieve the AND goal, by intersecting each of the key sets.
      traceIds = Futures.transform(allAsList(futureKeySetsToIntersect), CassandraUtil.intersectKeySets());
    }
    return transform(traceIds, new AsyncFunction<Set<Long>, List<List<Span>>>() {
      @Override
      public ListenableFuture<List<List<Span>>> apply(@Nullable Set<Long> traceIds) {
        traceIds = ImmutableSet.copyOf(Iterators.limit(traceIds.iterator(), request.limit));
        return transform(getSpansByTraceIds(traceIds, maxTraceCols),
            new Function<List<Span>, List<List<Span>>>() {
              @Override public List<List<Span>> apply(@Nullable List<Span> input) {
                // Indexes only contain Span.traceId, so our matches are imprecise on Span.traceIdHigh
                return FluentIterable.from(GroupByTraceId.apply(input, strictTraceId, true))
                    .filter(trace -> trace.get(0).traceIdHigh == 0 || request.test(trace))
                    .toList();
              }
            });
      }

      @Override public String toString() {
        return "getSpansByTraceIds";
      }
    });
  }