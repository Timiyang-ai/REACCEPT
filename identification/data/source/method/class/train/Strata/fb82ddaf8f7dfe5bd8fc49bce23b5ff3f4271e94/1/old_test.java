  @Test
  public void test_combineWith_same() {
    Iterable<LocalDate> holidays = Arrays.asList(WED_2014_07_16);
    ImmutableHolidayCalendar base = ImmutableHolidayCalendar.of(TEST_ID, holidays, SATURDAY, SUNDAY);
    HolidayCalendar test = base.combinedWith(base);
    assertThat(test).isSameAs(base);
  }