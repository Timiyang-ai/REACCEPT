  @Test
  public void test_getResultCalendar1() {
    DaysAdjustment test = DaysAdjustment.ofBusinessDays(3, SAT_SUN);
    assertThat(test.getResultCalendar()).isEqualTo(SAT_SUN);
  }