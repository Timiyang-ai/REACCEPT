public SchedulePeriod toAdjusted(BusinessDayAdjustment businessDayAdjustment) {
    // implementation needs to return 'this' if unchanged to optimize downstream code
    LocalDate resultStart = businessDayAdjustment.adjust(startDate);
    LocalDate resultEnd = businessDayAdjustment.adjust(endDate);
    if (resultStart.equals(startDate) && resultEnd.equals(endDate)) {
      return this;
    }
    return of(resultStart, resultEnd, unadjustedStartDate, unadjustedEndDate);
  }