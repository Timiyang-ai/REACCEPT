  @Test
  public void test_currencyExposure() {
    MultiCurrencyAmount ceComputed = PRICER_TRADE.currencyExposure(OPTION_TRADE, RATES_PROVIDER, VOLS);
    MultiCurrencyAmount ceExpected = PRICER_PRODUCT.currencyExposure(OPTION_PRODUCT, RATES_PROVIDER, VOLS)
        .plus(PRICER_PAYMENT.presentValue(PREMIUM, RATES_PROVIDER));
    assertThat(ceComputed.size()).isEqualTo(2);
    assertThat(ceComputed.getAmount(EUR).getAmount()).isCloseTo(ceExpected.getAmount(EUR).getAmount(), offset(TOL * NOTIONAL));
    assertThat(ceComputed.getAmount(USD).getAmount()).isCloseTo(ceExpected.getAmount(USD).getAmount(), offset(TOL * NOTIONAL));
  }