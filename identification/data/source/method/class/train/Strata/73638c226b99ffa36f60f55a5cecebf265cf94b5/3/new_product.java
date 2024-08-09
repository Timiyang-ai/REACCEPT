public LocalDateDoubleTimeSeriesBuilder putAll(Stream<LocalDateDoublePoint> points) {
    ArgChecker.notNull(points, "points");
    points.forEach(this::put);
    return this;
  }