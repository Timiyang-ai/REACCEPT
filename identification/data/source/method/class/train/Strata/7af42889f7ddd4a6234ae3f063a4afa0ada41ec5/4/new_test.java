  @Test
  public void equals() {
    PeriodAdjustment a = PeriodAdjustment.of(Period.ofMonths(3), LAST_DAY, BDA_FOLLOW_SAT_SUN);
    PeriodAdjustment b = PeriodAdjustment.of(Period.ofMonths(1), LAST_DAY, BDA_FOLLOW_SAT_SUN);
    PeriodAdjustment c = PeriodAdjustment.of(Period.ofMonths(3), PAC_NONE, BDA_FOLLOW_SAT_SUN);
    PeriodAdjustment d = PeriodAdjustment.of(Period.ofMonths(3), LAST_DAY, BDA_NONE);
    assertThat(a.equals(b)).isEqualTo(false);
    assertThat(a.equals(c)).isEqualTo(false);
    assertThat(a.equals(d)).isEqualTo(false);
  }