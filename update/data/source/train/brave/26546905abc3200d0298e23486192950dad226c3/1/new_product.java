void reportOrphanedSpans() {
    Reference<? extends TraceContext> reference;
    // This is called on critical path of unrelated traced operations. If we have orphaned spans, be
    // careful to not penalize the performance of the caller. It is better to cache time when
    // flushing a span than hurt performance of unrelated operations by calling
    // currentTimeMicroseconds N times
    long flushTime = 0L;
    while ((reference = poll()) != null) {
      MutableSpan value = delegate.remove(reference);
      if (value == null || noop.get()) continue;
      if (flushTime == 0L) flushTime = clock.currentTimeMicroseconds();
      value.annotate(flushTime, "brave.flush");
      reporter.report(value.toSpan(endpoint));
    }
  }