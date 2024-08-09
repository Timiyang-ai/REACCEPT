public LocalDateDoubleTimeSeriesBuilder putAll(LocalDate[] dates, double[] values) {
    ArgChecker.isTrue(dates.length == values.length,
        "Arrays are of different sizes - dates: {}, values: {}", dates.length, values.length);
    for (int i = 0; i < dates.length; i++) {
      entries.put(dates[i], values[i]);
    }
    return this;
  }