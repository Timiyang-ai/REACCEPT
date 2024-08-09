  @Test
  public void test_yearFraction() {
    SchedulePeriod test = SchedulePeriod.of(JUN_16, JUL_18, JUN_16, JUL_17);
    Schedule schedule = Schedule.ofTerm(test);
    assertThat(test.yearFraction(DayCounts.ACT_360, schedule))
        .isEqualTo(DayCounts.ACT_360.yearFraction(JUN_16, JUL_18, schedule), TOLERANCE);
  }