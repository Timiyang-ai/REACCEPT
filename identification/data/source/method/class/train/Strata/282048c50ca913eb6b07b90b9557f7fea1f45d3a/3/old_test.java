  @Test
  public void test_currencyExposure() {
    MultiCurrencyAmount computed1 = PRODUCT_PRICER.currencyExposure(CMS_ONE_LEG, RATES_PROVIDER, VOLATILITIES);
    MultiCurrencyAmount computed2 = PRODUCT_PRICER.currencyExposure(CMS_TWO_LEGS, RATES_PROVIDER, VOLATILITIES);
    MultiCurrencyAmount pv1 = PRODUCT_PRICER.presentValue(CMS_ONE_LEG, RATES_PROVIDER, VOLATILITIES);
    PointSensitivityBuilder pt1 = PRODUCT_PRICER.presentValueSensitivityRates(CMS_ONE_LEG, RATES_PROVIDER, VOLATILITIES);
    MultiCurrencyAmount expected1 = RATES_PROVIDER.currencyExposure(pt1.build()).plus(pv1);
    MultiCurrencyAmount pv2 = PRODUCT_PRICER.presentValue(CMS_TWO_LEGS, RATES_PROVIDER, VOLATILITIES);
    PointSensitivityBuilder pt2 = PRODUCT_PRICER.presentValueSensitivityRates(CMS_TWO_LEGS, RATES_PROVIDER, VOLATILITIES);
    MultiCurrencyAmount expected2 = RATES_PROVIDER.currencyExposure(pt2.build()).plus(pv2);
    assertThat(computed1.getAmount(EUR).getAmount()).isCloseTo(expected1.getAmount(EUR).getAmount(), offset(NOTIONAL_VALUE * TOL));
    assertThat(computed2.getAmount(EUR).getAmount()).isCloseTo(expected2.getAmount(EUR).getAmount(), offset(NOTIONAL_VALUE * TOL));
  }