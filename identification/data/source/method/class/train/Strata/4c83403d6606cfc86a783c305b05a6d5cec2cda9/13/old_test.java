  @Test
  public void test_currencyExposure() {
    CurrencyAmount pv = PRICER.presentValue(NDF, PROVIDER);
    MultiCurrencyAmount ce = PRICER.currencyExposure(NDF, PROVIDER);
    CurrencyAmount ceConverted = ce.convertedTo(pv.getCurrency(), PROVIDER);
    assertThat(pv.getAmount()).isCloseTo(ceConverted.getAmount(), offset(NOMINAL_USD * TOL));
  }