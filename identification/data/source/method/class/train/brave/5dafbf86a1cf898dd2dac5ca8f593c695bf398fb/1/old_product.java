public boolean remove(TraceContext context) {
    if (context == null) throw new NullPointerException("context == null");
    LookupKey lookupKey = getLookupKey();
    lookupKey.set(context);
    PendingSpan last = delegate.remove(lookupKey);
    reportOrphanedSpans(); // also clears the reference relating to the recent remove
    return last != null;
  }