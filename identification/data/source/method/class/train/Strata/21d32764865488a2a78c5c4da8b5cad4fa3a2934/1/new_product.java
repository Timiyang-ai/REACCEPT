@Override
  public LocalDateDoubleTimeSeries subSeries(LocalDate startInclusive, LocalDate endExclusive) {
    ArgChecker.notNull(startInclusive, "startInclusive");
    ArgChecker.notNull(endExclusive, "endExclusive");
    if (endExclusive.isBefore(startInclusive)) {
      throw new IllegalArgumentException(
          "Invalid sub series, end before start: " + startInclusive + " to " + endExclusive);
    }
    // special case when this is empty or when the dates are the same
    if (isEmpty() || startInclusive.equals(endExclusive)) {
      return EMPTY;
    }
    // where in the array would start/end be (whether or not it's actually in the series)
    int startPos = Arrays.binarySearch(dates, startInclusive);
    startPos = startPos >= 0 ? startPos : -startPos - 1;
    int endPos = Arrays.binarySearch(dates, endExclusive);
    endPos = endPos >= 0 ? endPos : -endPos - 1;
    // create sub-series
    LocalDate[] timesArray = Arrays.copyOfRange(dates, startPos, endPos);
    double[] valuesArray = Arrays.copyOfRange(values, startPos, endPos);
    return createUnsafe(timesArray, valuesArray);
  }