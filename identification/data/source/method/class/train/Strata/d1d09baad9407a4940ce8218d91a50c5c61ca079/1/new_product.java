public static RateAccrualPeriod.Builder builder(SchedulePeriod period) {
    return builder()
        .startDate(period.getStartDate())
        .endDate(period.getEndDate())
        .unadjustedStartDate(period.getStartDate())
        .unadjustedEndDate(period.getUnadjustedEndDate());
  }