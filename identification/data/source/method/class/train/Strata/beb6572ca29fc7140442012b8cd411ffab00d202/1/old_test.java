  @Test
  public void test_optionId_monthly() {
    SecurityId test = EtdIdUtils.optionId(ExchangeIds.ECAG, FGBS, YearMonth.of(2017, 6), MONTHLY, 0, PutCall.PUT, 12.34);
    assertThat(test.getStandardId()).isEqualTo(StandardId.of("OG-ETD", "O-ECAG-FGBS-201706-P12.34"));
  }