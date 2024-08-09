  @Test
  public void test_parseCurrencyAmount() {
    ImmutableList<String> headers = ImmutableList.of("CCY", "AMT");
    ImmutableList<String> firstRow = ImmutableList.of("GBP", "123.4");
    CsvRow row = CsvFile.of(headers, ImmutableList.of(firstRow)).row(0);
    assertThat(CsvLoaderUtils.parseCurrencyAmount(row, "CCY", "AMT")).isEqualTo(CurrencyAmount.of(Currency.GBP, 123.4));
  }