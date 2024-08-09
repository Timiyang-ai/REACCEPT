  @Test
  public void test_fxRate_CurrencyPair() {
    RatesProvider mockProv = new MockRatesProvider();
    assertThat(mockProv.fxRate(CurrencyPair.of(GBP, USD))).isEqualTo(RATE);
  }