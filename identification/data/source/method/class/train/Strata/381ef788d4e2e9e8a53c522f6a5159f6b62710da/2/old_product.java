public double yearFraction(DayCount dayCount) {
    ArgChecker.notNull(dayCount, "dayCount");
    return dayCount.yearFraction(startDate, endDate, this);
  }