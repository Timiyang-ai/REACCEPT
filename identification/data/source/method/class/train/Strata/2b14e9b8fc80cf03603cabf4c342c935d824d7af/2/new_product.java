public PeriodicSchedule subSchedule(
      Frequency frequency,
      RollConvention rollConvention,
      StubConvention stubConvention,
      BusinessDayAdjustment adjustment) {

    return PeriodicSchedule.builder()
        .startDate(unadjustedStartDate)
        .endDate(unadjustedEndDate)
        .frequency(frequency)
        .businessDayAdjustment(adjustment)
        .rollConvention(rollConvention)
        .stubConvention(stubConvention)
        .build();
  }