  @Test
  public void test_equals() {
    ImmutableHolidayCalendar a1 = ImmutableHolidayCalendar.of(TEST_ID, Arrays.asList(WED_2014_07_16), SATURDAY, SUNDAY);
    ImmutableHolidayCalendar a2 = ImmutableHolidayCalendar.of(TEST_ID, Arrays.asList(WED_2014_07_16), SATURDAY, SUNDAY);
    ImmutableHolidayCalendar b = ImmutableHolidayCalendar.of(TEST_ID2, Arrays.asList(WED_2014_07_16), SATURDAY, SUNDAY);
    ImmutableHolidayCalendar c = ImmutableHolidayCalendar.of(TEST_ID, Arrays.asList(THU_2014_07_10), SATURDAY, SUNDAY);
    assertThat(a1.equals(a2)).isEqualTo(true);
    assertThat(a1.equals(b)).isEqualTo(false);
    assertThat(a1.equals(c)).isEqualTo(true);  // only name compared
  }