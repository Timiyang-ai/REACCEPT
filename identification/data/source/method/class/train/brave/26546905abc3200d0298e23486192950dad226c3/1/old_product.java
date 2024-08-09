void reportOrphanedSpans() {
    Reference<? extends TraceContext> reference;
    while ((reference = poll()) != null) {
      TraceContext context = reference.get();
      MutableSpan value = delegate.remove(reference);
      if (value == null || noop.get()) continue;
      try {
        value.annotate(value.clock.currentTimeMicroseconds(), "brave.flush");
        reporter.report(value.toSpan(endpoint));
      } catch (RuntimeException e) {
        // don't crash the caller if there was a problem reporting an unrelated span.
        if (context != null && logger.isLoggable(Level.FINE)) {
          logger.log(Level.FINE, "error flushing " + context, e);
        }
      }
    }
  }