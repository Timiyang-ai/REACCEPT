public Schedule subSchedule(
      Frequency frequency,
      RollConvention rollConvention,
      StubConvention stubConvention,
      BusinessDayAdjustment adjustment) {
    ArgChecker.notNull(frequency, "frequency");
    ArgChecker.notNull(rollConvention, "rollConvention");
    ArgChecker.notNull(stubConvention, "stubConvention");
    ArgChecker.notNull(adjustment, "adjustment");
    return PeriodicSchedule.builder()
        .startDate(unadjustedStartDate)
        .endDate(unadjustedEndDate)
        .frequency(frequency)
        .businessDayAdjustment(adjustment)
        .rollConvention(rollConvention)
        .stubConvention(stubConvention)
        .build()
        .createSchedule();
  }