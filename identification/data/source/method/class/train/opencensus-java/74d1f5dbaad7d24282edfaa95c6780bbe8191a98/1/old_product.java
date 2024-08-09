public static long toMillis(Duration duration) {
    return TimeUnit.SECONDS.toMillis(duration.getSeconds())
        + TimeUnit.NANOSECONDS.toMillis(duration.getNanos());
  }