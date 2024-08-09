public LocalDateDoubleTimeSeriesBuilder putAll(LocalDateDoubleTimeSeriesBuilder other) {
    ArgChecker.notNull(other, "other");
    entries.putAll(other.entries);
    return this;
  }