  @Test
  public void test_futureId_monthly() {
    SecurityId test = EtdIdUtils.futureId(ExchangeIds.ECAG, FGBS, YearMonth.of(2017, 6), MONTHLY);
    assertThat(test.getStandardId()).isEqualTo(StandardId.of("OG-ETD", "F-ECAG-FGBS-201706"));
  }