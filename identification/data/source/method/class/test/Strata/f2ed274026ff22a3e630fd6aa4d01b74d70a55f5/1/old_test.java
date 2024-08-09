  @Test
  public void test_combinedWith() {
    HolidayCalendarId gb = HolidayCalendarId.of("GB");
    HolidayCalendarId eu = HolidayCalendarId.of("EU");
    HolidayCalendarId us = HolidayCalendarId.of("US");
    HolidayCalendarId combined1 = eu.combinedWith(us).combinedWith(gb);
    HolidayCalendarId combined2 = us.combinedWith(eu).combinedWith(gb.combinedWith(us));
    assertThat(combined1.getName()).isEqualTo("EU+GB+US");
    assertThat(combined1.toString()).isEqualTo("EU+GB+US");
    assertThat(combined2.getName()).isEqualTo("EU+GB+US");
    assertThat(combined2.toString()).isEqualTo("EU+GB+US");
    assertThat(combined1.equals(combined2)).isEqualTo(true);
  }