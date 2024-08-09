  @Test
  public void test_currencyExposure() {
    ImmutableRatesProvider prov = ImmutableRatesProvider.builder(VAL_DATE)
        .discountCurve(GBP, DISCOUNT_CURVE_GBP)
        .build();
    MultiCurrencyAmount computed = PRICER.currencyExposure(PERIOD, prov);
    PointSensitivities point = PRICER.presentValueSensitivity(PERIOD, prov).build();
    MultiCurrencyAmount expected = prov.currencyExposure(point)
        .plus(CurrencyAmount.of(GBP, PRICER.presentValue(PERIOD, prov)));
    assertThat(computed).isEqualTo(expected);
  }