Function<SchedulePeriod, Schedule> resolve(RollConvention rollConvention, ReferenceData refData) {
    return accrualPeriod -> accrualPeriod.subSchedule(
        resetFrequency, rollConvention, StubConvention.SHORT_FINAL, businessDayAdjustment);
  }