public long toMillis() {
    return TimeUnit.SECONDS.toMillis(getSeconds()) + TimeUnit.NANOSECONDS.toMillis(getNanos());
  }