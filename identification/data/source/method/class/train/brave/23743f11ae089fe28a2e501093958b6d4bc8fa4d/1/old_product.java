void reportOrphanedSpans() {
    Reference<? extends TraceContext> reference;
    while ((reference = poll()) != null) {
      TraceContext context = reference.get();
      MutableSpan value = delegate.remove(reference);
      if (value == null) continue;
      try {
        value.annotate(clock.currentTimeMicroseconds(), "brave.flush");
        reporter.report(value.toSpan());
      } catch (RuntimeException e) {
        // don't crash the caller if there was a problem reporting an unrelated span.
        if (context != null && logger.isLoggable(Level.FINE)) {
          logger.log(Level.FINE, "error flushing " + context, e);
        }
      }
    }
  }