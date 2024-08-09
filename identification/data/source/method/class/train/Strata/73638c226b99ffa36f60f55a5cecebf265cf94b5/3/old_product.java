public LocalDateDoubleTimeSeriesBuilder putAll(Stream<LocalDateDoublePoint> points) {
    ArgChecker.notNull(points, "points");
    points.forEachOrdered(point -> put(point));
    return this;
  }