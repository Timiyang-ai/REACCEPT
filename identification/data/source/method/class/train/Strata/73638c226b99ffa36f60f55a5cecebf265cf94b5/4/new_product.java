public LocalDateDoubleTimeSeriesBuilder putAll(Collection<LocalDate> dates, Collection<Double> values) {
    ArgChecker.noNulls(dates, "dates");
    ArgChecker.noNulls(values, "values");
    ArgChecker.isTrue(dates.size() == values.size(),
        "Arrays are of different sizes - dates: {}, values: {}", dates.size(), values.size());
    Iterator<LocalDate> itDate = dates.iterator();
    Iterator<Double> itValue = values.iterator();
    for (int i = 0; i < dates.size(); i++) {
      put(itDate.next(), itValue.next());
    }
    return this;
  }