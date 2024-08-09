void reportOrphanedSpans() {
    RealKey contextKey;
    // This is called on critical path of unrelated traced operations. If we have orphaned spans, be
    // careful to not penalize the performance of the caller. It is better to cache time when
    // flushing a span than hurt performance of unrelated operations by calling
    // currentTimeMicroseconds N times
    long flushTime = 0L;
    boolean noop = zipkinHandler == FinishedSpanHandler.NOOP || this.noop.get();
    while ((contextKey = (RealKey) poll()) != null) {
      PendingSpan value = delegate.remove(contextKey);
      if (noop || value == null || !contextKey.sampled) continue;
      if (flushTime == 0L) flushTime = clock.currentTimeMicroseconds();

      TraceContext context = InternalPropagation.instance.newTraceContext(
          InternalPropagation.FLAG_SAMPLED_SET | InternalPropagation.FLAG_SAMPLED,
          contextKey.traceIdHigh, contextKey.traceId,
          0L, contextKey.spanId,
          Collections.emptyList()
      );
      value.state.annotate(flushTime, "brave.flush");

      try {
        zipkinHandler.handle(context, value.state);
      } catch (RuntimeException e) {
        Platform.get().log("error reporting {0}", context, e);
      }
    }
  }