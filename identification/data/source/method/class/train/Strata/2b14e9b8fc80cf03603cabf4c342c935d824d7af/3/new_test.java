  @Test
  public void test_toAdjusted() {
    SchedulePeriod period1 = SchedulePeriod.of(JUN_15, SEP_17);
    SchedulePeriod period2 = SchedulePeriod.of(SEP_17, SEP_30);
    Schedule test = Schedule.builder()
        .periods(period1, period2)
        .frequency(P3M)
        .rollConvention(DAY_17)
        .build();
    assertThat(test.toAdjusted(date -> date)).isEqualTo(test);
    assertThat(test.toAdjusted(date -> date.equals(JUN_15) ? JUN_16 : date))
        .isEqualTo(Schedule.builder()
            .periods(SchedulePeriod.of(JUN_16, SEP_17, JUN_15, SEP_17), period2)
            .frequency(P3M)
            .rollConvention(DAY_17)
            .build());
  }