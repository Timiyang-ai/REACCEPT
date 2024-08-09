  @Test
  public void test_toUnadjusted() {
    SchedulePeriod a = SchedulePeriod.of(JUL_17, OCT_17, JUL_16, OCT_15);
    SchedulePeriod b = SchedulePeriod.of(JUL_16, OCT_15, JUL_16, OCT_15);
    Schedule test = Schedule.builder()
        .periods(ImmutableList.of(a))
        .frequency(P1M)
        .rollConvention(DAY_17)
        .build()
        .toUnadjusted();
    Schedule expected = Schedule.builder()
        .periods(ImmutableList.of(b))
        .frequency(P1M)
        .rollConvention(DAY_17)
        .build();
    assertThat(test).isEqualTo(expected);
  }