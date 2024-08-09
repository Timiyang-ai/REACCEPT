  @Test
  public void test_subSchedule_1monthIn3Month() {
    SchedulePeriod test = SchedulePeriod.of(JUN_17, SEP_17);
    Schedule schedule = test.subSchedule(P1M, RollConventions.DAY_17, StubConvention.NONE, BusinessDayAdjustment.NONE)
        .createSchedule(REF_DATA);
    assertThat(schedule.size()).isEqualTo(3);
    assertThat(schedule.getPeriod(0)).isEqualTo(SchedulePeriod.of(JUN_17, JUL_17));
    assertThat(schedule.getPeriod(1)).isEqualTo(SchedulePeriod.of(JUL_17, AUG_17));
    assertThat(schedule.getPeriod(2)).isEqualTo(SchedulePeriod.of(AUG_17, SEP_17));
    assertThat(schedule.getFrequency()).isEqualTo(P1M);
    assertThat(schedule.getRollConvention()).isEqualTo(RollConventions.DAY_17);
  }