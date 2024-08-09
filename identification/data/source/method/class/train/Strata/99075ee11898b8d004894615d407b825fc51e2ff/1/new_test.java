  @Test
  public void test_normalized() {
    DaysAdjustment zeroDays = DaysAdjustment.ofCalendarDays(0, BDA_FOLLOW_SAT_SUN);
    DaysAdjustment zeroDaysWithCalendar = DaysAdjustment.ofBusinessDays(0, WED_THU, BDA_FOLLOW_SAT_SUN);
    DaysAdjustment twoDays = DaysAdjustment.ofCalendarDays(2, BDA_FOLLOW_SAT_SUN);
    DaysAdjustment twoDaysWithCalendar = DaysAdjustment.ofBusinessDays(2, WED_THU, BDA_FOLLOW_SAT_SUN);
    DaysAdjustment twoDaysWithSameCalendar = DaysAdjustment.ofBusinessDays(2, SAT_SUN, BDA_FOLLOW_SAT_SUN);
    DaysAdjustment twoDaysWithNoAdjust = DaysAdjustment.ofBusinessDays(2, SAT_SUN);
    assertThat(zeroDays.normalized()).isEqualTo(zeroDays);
    assertThat(zeroDaysWithCalendar.normalized()).isEqualTo(zeroDays);
    assertThat(twoDays.normalized()).isEqualTo(twoDays);
    assertThat(twoDaysWithCalendar.normalized()).isEqualTo(twoDaysWithCalendar);
    assertThat(twoDaysWithSameCalendar.normalized()).isEqualTo(twoDaysWithNoAdjust);
    assertThat(twoDaysWithNoAdjust.normalized()).isEqualTo(twoDaysWithNoAdjust);
  }