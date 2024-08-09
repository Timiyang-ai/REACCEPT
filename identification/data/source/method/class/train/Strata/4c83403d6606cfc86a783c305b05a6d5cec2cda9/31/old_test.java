  @Test
  public void test_currencyExposure() {
    MultiCurrencyAmount computed = PRICER.currencyExposure(FWD, PROVIDER);
    MultiCurrencyAmount expected = PRICER.presentValue(FWD, PROVIDER);
    assertThat(computed).isEqualTo(expected);
  }