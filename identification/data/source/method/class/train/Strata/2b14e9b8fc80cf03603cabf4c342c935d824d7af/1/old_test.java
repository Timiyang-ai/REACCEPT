  @Test
  public void test_toAdjusted() {
    SchedulePeriod test1 = SchedulePeriod.of(JUN_15, SEP_17);
    assertThat(test1.toAdjusted(date -> date)).isEqualTo(test1);
    assertThat(test1.toAdjusted(date -> date.equals(JUN_15) ? JUN_16 : date))
        .isEqualTo(SchedulePeriod.of(JUN_16, SEP_17, JUN_15, SEP_17));
    SchedulePeriod test2 = SchedulePeriod.of(JUN_16, AUG_17);
    assertThat(test2.toAdjusted(date -> date.equals(AUG_17) ? AUG_18 : date))
        .isEqualTo(SchedulePeriod.of(JUN_16, AUG_18, JUN_16, AUG_17));
  }