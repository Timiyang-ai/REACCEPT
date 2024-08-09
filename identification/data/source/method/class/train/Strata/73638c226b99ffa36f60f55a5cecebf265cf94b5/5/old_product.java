public LocalDateDoubleTimeSeriesBuilder putAll(Map<LocalDate, Double> map) {
    ArgChecker.noNulls(map, "map");
    entries.putAll(map);
    return this;
  }