public CallOptions withDeadlineAfter(long duration, TimeUnit unit) {
    return withDeadlineNanoTime(System.nanoTime() + unit.toNanos(duration));
  }