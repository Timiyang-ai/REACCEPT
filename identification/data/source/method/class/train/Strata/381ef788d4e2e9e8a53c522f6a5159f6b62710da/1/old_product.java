public double yearFraction(DayCount dayCount) {
    ArgChecker.notNull(dayCount, "dayCount");
    return dayCount.getDayCountFraction(startDate, endDate, this);
  }