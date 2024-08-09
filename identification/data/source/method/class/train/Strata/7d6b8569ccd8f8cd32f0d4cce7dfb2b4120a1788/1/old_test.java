  @Test
  public void test_standard() {
    ReferenceData test = ReferenceData.standard();
    assertThat(test.containsValue(HolidayCalendarIds.NO_HOLIDAYS)).isEqualTo(true);
    assertThat(test.containsValue(HolidayCalendarIds.SAT_SUN)).isEqualTo(true);
    assertThat(test.containsValue(HolidayCalendarIds.FRI_SAT)).isEqualTo(true);
    assertThat(test.containsValue(HolidayCalendarIds.THU_FRI)).isEqualTo(true);
    assertThat(test.containsValue(HolidayCalendarIds.GBLO)).isEqualTo(true);
  }