protected Duration getNextTimeoutInterval() {
    Duration nextTimeoutInterval = INFINITE_FUTURE;
    if (!timers.isEmpty()) {
      // The time recorded in timer is in nano-seconds. We have to convert it to milli-seconds
      // We need to ceil the result to avoid early wake up
      nextTimeoutInterval = timers.peek().expirationTime.minusNanos(System.nanoTime());
    }
    return nextTimeoutInterval;
  }