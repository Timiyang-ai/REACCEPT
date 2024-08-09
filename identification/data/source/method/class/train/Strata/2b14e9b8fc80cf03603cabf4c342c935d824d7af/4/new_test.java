  @Test
  public void test_resolve() {
    ResetSchedule test = ResetSchedule.builder()
        .resetFrequency(P1M)
        .businessDayAdjustment(BusinessDayAdjustment.of(FOLLOWING, GBLO))
        .build();
    SchedulePeriod accrualPeriod = SchedulePeriod.of(DATE_01_06, DATE_04_07, DATE_01_05, DATE_04_05);
    Schedule schedule = test.createSchedule(DAY_5, REF_DATA).apply(accrualPeriod);
    Schedule expected = Schedule.builder()
        .periods(
            SchedulePeriod.of(DATE_01_06, DATE_02_05, DATE_01_05, DATE_02_05),
            SchedulePeriod.of(DATE_02_05, DATE_03_05, DATE_02_05, DATE_03_05),
            SchedulePeriod.of(DATE_03_05, DATE_04_07, DATE_03_05, DATE_04_05))
        .frequency(P1M)
        .rollConvention(DAY_5)
        .build();
    assertThat(schedule).isEqualTo(expected);
  }