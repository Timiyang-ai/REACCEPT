public boolean remove(TraceContext context) {
    if (context == null) throw new NullPointerException("context == null");
    PendingSpan last = delegate.remove(context);
    reportOrphanedSpans(); // also clears the reference relating to the recent remove
    return last != null;
  }