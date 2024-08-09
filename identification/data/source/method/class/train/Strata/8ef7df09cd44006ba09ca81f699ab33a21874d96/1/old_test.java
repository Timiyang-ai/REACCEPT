  @Test
  public void test_defaultByCurrency() {
    assertThat(HolidayCalendarId.defaultByCurrency(Currency.GBP)).isEqualTo(HolidayCalendarIds.GBLO);
    assertThat(HolidayCalendarId.defaultByCurrency(Currency.CZK)).isEqualTo(HolidayCalendarIds.CZPR);
    assertThat(HolidayCalendarId.defaultByCurrency(Currency.HKD)).isEqualTo(HolidayCalendarId.of("HKHK"));
    assertThatIllegalArgumentException().isThrownBy(() -> HolidayCalendarId.defaultByCurrency(Currency.XAG));
  }