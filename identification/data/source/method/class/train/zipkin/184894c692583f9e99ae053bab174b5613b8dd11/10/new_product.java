@Override
  public ListenableFuture<List<List<Span>>> getTraces(final QueryRequest request) {
    ListenableFuture<Map<Long, Long>> traceIdToTimestamp;
    if (request.minDuration != null || request.maxDuration != null) {
      traceIdToTimestamp = getTraceIdsByDuration(request);
    } else if (request.spanName != null) {
      traceIdToTimestamp = getTraceIdsBySpanName(request.serviceName, request.spanName,
          request.endTs * 1000, request.lookback * 1000, request.limit);
    } else if (request.serviceName != null) {
      traceIdToTimestamp = getTraceIdsByServiceNames(Collections.singletonList(request.serviceName),
          request.endTs * 1000, request.lookback * 1000, request.limit);
    } else {
      checkArgument(selectTraceIdsByServiceNames != null,
          "getTraces without serviceName requires Cassandra 2.2 or later");
      traceIdToTimestamp = transform(getServiceNames(),
          new AsyncFunction<List<String>, Map<Long, Long>>() {
            @Override public ListenableFuture<Map<Long, Long>> apply(List<String> serviceNames) {
              return getTraceIdsByServiceNames(serviceNames,
                  request.endTs * 1000, request.lookback * 1000, request.limit);
            }
          });
    }

    List<String> annotationKeys = annotationKeys(request);

    ListenableFuture<Set<Long>> traceIds;
    if (annotationKeys.isEmpty()) {
      // Simplest case is when there is no annotation query. Limit is valid since there's no AND
      // query that could reduce the results returned to less than the limit.
      traceIds = transform(traceIdToTimestamp, keyset());
    } else {
      // While a valid port of the scala cassandra span store (from zipkin 1.35), there is a fault.
      // each annotation key is an intersection, which means we are likely to return < limit.
      List<ListenableFuture<Map<Long, Long>>> futureKeySetsToIntersect = new ArrayList<>();
      futureKeySetsToIntersect.add(traceIdToTimestamp);
      for (String annotationKey : annotationKeys) {
        futureKeySetsToIntersect.add(getTraceIdsByAnnotation(annotationKey,
            request.endTs * 1000, request.lookback * 1000, request.limit));
      }
      // We achieve the AND goal, by intersecting each of the key sets.
      traceIds = transform(allAsList(futureKeySetsToIntersect), intersectKeySets());
    }
    return transform(traceIds, new AsyncFunction<Set<Long>, List<List<Span>>>() {
      @Override public ListenableFuture<List<List<Span>>> apply(Set<Long> traceIds) {
        return transform(getSpansByTraceIds(traceIds, maxTraceCols), AdjustTraces.INSTANCE);
      }

      @Override public String toString() {
        return "getSpansByTraceIds";
      }
    });
  }