public CallOptions withDeadlineAfter(long duration, TimeUnit unit) {
    return withDeadline(Deadline.after(duration, unit));
  }