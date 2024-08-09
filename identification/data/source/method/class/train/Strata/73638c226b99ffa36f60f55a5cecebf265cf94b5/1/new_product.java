public LocalDateDoubleTimeSeriesBuilder putAll(LocalDateDoubleTimeSeriesBuilder other) {
    ArgChecker.notNull(other, "other");
    entries.putAll(other.entries);
    containsWeekends = containsWeekends || other.containsWeekends;
    return this;
  }