void reportOrphanedSpans() {
    RealKey contextKey;
    // This is called on critical path of unrelated traced operations. If we have orphaned spans, be
    // careful to not penalize the performance of the caller. It is better to cache time when
    // flushing a span than hurt performance of unrelated operations by calling
    // currentTimeMicroseconds N times
    Span.Builder builder = null;
    long flushTime = 0L;
    while ((contextKey = (RealKey) poll()) != null) {
      PendingSpan value = delegate.remove(contextKey);
      if (value == null || noop.get() || !contextKey.sampled) continue;
      if (builder != null) {
        builder.clear();
      } else {
        builder = Span.newBuilder();
        flushTime = clock.currentTimeMicroseconds();
      }

      zipkin2.Span.Builder builderWithContextData = zipkin2.Span.newBuilder()
          .traceId(contextKey.traceIdHigh, contextKey.traceId)
          .id(contextKey.spanId)
          .localEndpoint(localEndpoint)
          .addAnnotation(flushTime, "brave.flush");

      value.state.writeTo(builderWithContextData);
      reporter.report(builderWithContextData.build());
    }
  }