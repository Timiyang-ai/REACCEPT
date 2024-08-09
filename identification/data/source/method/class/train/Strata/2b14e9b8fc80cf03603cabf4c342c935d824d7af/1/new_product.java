public SchedulePeriod toAdjusted(DateAdjuster adjuster) {
    // implementation needs to return 'this' if unchanged to optimize downstream code
    LocalDate resultStart = adjuster.adjust(startDate);
    LocalDate resultEnd = adjuster.adjust(endDate);
    if (resultStart.equals(startDate) && resultEnd.equals(endDate)) {
      return this;
    }
    return of(resultStart, resultEnd, unadjustedStartDate, unadjustedEndDate);
  }