  @Test
  public void test_parseAdjustableDate() {
    ImmutableList<String> headers = ImmutableList.of("DTE", "CNV", "CAL");
    ImmutableList<String> firstRow = ImmutableList.of("2019-03-01", "F", "GBLO");
    CsvRow row = CsvFile.of(headers, ImmutableList.of(firstRow)).row(0);
    assertThat(CsvLoaderUtils.parseAdjustableDate(row, "DTE", "CNV", "CAL")).isEqualTo(AdjustableDate.of(
        LocalDate.of(2019, 3, 1),
        BusinessDayAdjustment.of(BusinessDayConventions.FOLLOWING, HolidayCalendarIds.GBLO)));
  }