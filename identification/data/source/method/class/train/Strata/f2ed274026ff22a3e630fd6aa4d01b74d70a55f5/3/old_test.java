  @Test
  public void test_combined() {
    ImmutableHolidayCalendar base1 =
        ImmutableHolidayCalendar.of(TEST_ID, ImmutableList.of(MON_2014_07_14), SATURDAY, SUNDAY);
    ImmutableHolidayCalendar base2 =
        ImmutableHolidayCalendar.of(TEST_ID2, ImmutableList.of(WED_2014_07_16), FRIDAY, SATURDAY);

    ImmutableHolidayCalendar test = ImmutableHolidayCalendar.combined(base1, base2);
    assertThat(test.getId()).isEqualTo(base1.getId().combinedWith(base2.getId()));
    assertThat(test.getName()).isEqualTo(base1.getId().combinedWith(base2.getId()).getName());
    assertThat(test.getHolidays()).containsExactly(MON_2014_07_14, WED_2014_07_16);
    assertThat(test.getWeekendDays()).containsExactly(FRIDAY, SATURDAY, SUNDAY);
  }