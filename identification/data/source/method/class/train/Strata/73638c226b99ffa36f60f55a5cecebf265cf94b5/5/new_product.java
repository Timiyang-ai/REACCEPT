public LocalDateDoubleTimeSeriesBuilder putAll(Map<LocalDate, Double> map) {
    ArgChecker.noNulls(map, "map");
    map.entrySet().forEach(e -> put(e.getKey(), e.getValue()));
    return this;
  }