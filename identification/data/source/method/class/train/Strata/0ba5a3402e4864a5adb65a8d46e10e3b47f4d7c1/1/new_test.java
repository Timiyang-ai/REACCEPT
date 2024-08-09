  @Test
  public void test_parseCurrencyAmountWithDirection() {
    ImmutableList<String> headers = ImmutableList.of("CCY", "AMT", "DIR");
    ImmutableList<String> firstRow = ImmutableList.of("GBP", "123.4", "Pay");
    CsvRow row = CsvFile.of(headers, ImmutableList.of(firstRow)).row(0);
    assertThat(CsvLoaderUtils.parseCurrencyAmountWithDirection(row, "CCY", "AMT", "DIR"))
        .isEqualTo(CurrencyAmount.of(Currency.GBP, -123.4));
  }