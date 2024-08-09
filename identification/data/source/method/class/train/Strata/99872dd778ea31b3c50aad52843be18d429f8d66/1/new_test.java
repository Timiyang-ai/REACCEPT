  @Test
  public void test_resolve_single() {
    HolidayCalendarId gb = HolidayCalendarId.of("GB");
    HolidayCalendarId eu = HolidayCalendarId.of("EU");
    HolidayCalendar gbCal = HolidayCalendars.SAT_SUN;
    ReferenceData refData = ImmutableReferenceData.of(gb, gbCal);
    assertThat(gb.resolve(refData)).isEqualTo(gbCal);
    assertThatExceptionOfType(ReferenceDataNotFoundException.class).isThrownBy(() -> eu.resolve(refData));
    assertThat(refData.getValue(gb)).isEqualTo(gbCal);
  }