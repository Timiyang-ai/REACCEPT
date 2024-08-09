@Override
  public Call<List<List<Span>>> getTraces(QueryRequest request) {
    if (!searchEnabled) return Call.emptyList();

    TimestampRange timestampRange = timestampRange(request);
    // If we have to make multiple queries, over fetch on indexes as they don't return distinct
    // (trace id, timestamp) rows. This mitigates intersection resulting in < limit traces
    final int traceIndexFetchSize = request.limit() * indexFetchMultiplier;
    List<Call<Map<String, Long>>> callsToIntersect = new ArrayList<>();

    List<String> annotationKeys = CassandraUtil.annotationKeys(request);
    if (null == spanTable && !annotationKeys.isEmpty()) {
      throw new IllegalStateException("The annotation_query index is not available");
    }
    for (String annotationKey : annotationKeys) {
      callsToIntersect.add(
          spanTable
              .newCall(request.serviceName(), annotationKey, timestampRange, traceIndexFetchSize)
              .map(CollapseToMap.INSTANCE));
    }

    // Bucketed calls can be expensive when service name isn't specified. This guards against abuse.
    if (request.spanName() != null || request.minDuration() != null || callsToIntersect.isEmpty()) {
      Call<Set<Entry<String, Long>>> bucketedTraceIdCall =
          newBucketedTraceIdCall(request, timestampRange, traceIndexFetchSize);
      callsToIntersect.add(bucketedTraceIdCall.map(CollapseToMap.INSTANCE));
    }

    if (callsToIntersect.size() == 1) {
      return callsToIntersect
          .get(0)
          .map(traceIdsSortedByDescTimestamp())
          .flatMap(spans.newFlatMapper(request));
    }

    // We achieve the AND goal, by intersecting each of the key sets.
    IntersectKeySets intersectedTraceIds = new IntersectKeySets(callsToIntersect);
    // @xxx the sorting by timestamp desc is broken here^
    return intersectedTraceIds.flatMap(spans.newFlatMapper(request));
  }