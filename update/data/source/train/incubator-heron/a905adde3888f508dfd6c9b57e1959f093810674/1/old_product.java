protected long getNextTimeoutIntervalMs() {
    long nextTimeoutIntervalMs = INFINITE_FUTURE;
    if (!timers.isEmpty()) {
      // The time recorded in timer is in nano-seconds. We have to convert it to milli-seconds
      // We need to ceil the result to avoid early wake up
      nextTimeoutIntervalMs =
          (timers.peek().getExpirationNs() - System.nanoTime()
          + Constants.MILLISECONDS_TO_NANOSECONDS) / Constants.MILLISECONDS_TO_NANOSECONDS;
    }
    return nextTimeoutIntervalMs;
  }