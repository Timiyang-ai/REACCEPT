  @Test
  public void test_currencyExposure() {
    MultiCurrencyAmount computed1 = TRADE_PRICER.currencyExposure(CMS_TRADE_PREMIUM, RATES_PROVIDER, VOLATILITIES);
    MultiCurrencyAmount computed2 = TRADE_PRICER.currencyExposure(CMS_TRADE, RATES_PROVIDER, VOLATILITIES);
    MultiCurrencyAmount pv1 = TRADE_PRICER.presentValue(CMS_TRADE_PREMIUM, RATES_PROVIDER, VOLATILITIES);
    PointSensitivities pt1 = TRADE_PRICER.presentValueSensitivityRates(CMS_TRADE_PREMIUM, RATES_PROVIDER, VOLATILITIES);
    MultiCurrencyAmount expected1 = RATES_PROVIDER.currencyExposure(pt1).plus(pv1);
    MultiCurrencyAmount pv2 = TRADE_PRICER.presentValue(CMS_TRADE, RATES_PROVIDER, VOLATILITIES);
    PointSensitivities pt2 = TRADE_PRICER.presentValueSensitivityRates(CMS_TRADE, RATES_PROVIDER, VOLATILITIES);
    MultiCurrencyAmount expected2 = RATES_PROVIDER.currencyExposure(pt2).plus(pv2);
    assertThat(computed1.getAmount(EUR).getAmount()).isCloseTo(expected1.getAmount(EUR).getAmount(), offset(NOTIONAL_VALUE * TOL));
    assertThat(computed2.getAmount(EUR).getAmount()).isCloseTo(expected2.getAmount(EUR).getAmount(), offset(NOTIONAL_VALUE * TOL));
  }