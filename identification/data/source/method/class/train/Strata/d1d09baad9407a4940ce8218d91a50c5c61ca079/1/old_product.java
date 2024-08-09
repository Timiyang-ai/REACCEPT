public static RateAccrualPeriod.Builder builder(SchedulePeriod period, DayCount dayCount) {
    return builder()
        .startDate(period.getStartDate())
        .endDate(period.getEndDate())
        .unadjustedStartDate(period.getStartDate())
        .unadjustedEndDate(period.getUnadjustedEndDate())
        .yearFraction(period.yearFraction(dayCount));
  }