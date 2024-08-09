  @Test
  public void test_currencyExposure() {
    ImmutableRatesProvider prov = ImmutableRatesProvider.builder(VAL_DATE)
        .discountCurve(GBP, DISCOUNT_CURVE_GBP)
        .build();
    DiscountingNotionalExchangePricer test = DiscountingNotionalExchangePricer.DEFAULT;
    MultiCurrencyAmount computed = test.currencyExposure(NOTIONAL_EXCHANGE_REC_GBP, prov);
    PointSensitivities point = test.presentValueSensitivity(NOTIONAL_EXCHANGE_REC_GBP, prov).build();
    MultiCurrencyAmount expected = prov.currencyExposure(point).plus(
        CurrencyAmount.of(NOTIONAL_EXCHANGE_REC_GBP.getCurrency(), test.presentValue(NOTIONAL_EXCHANGE_REC_GBP, prov)));
    assertThat(computed).isEqualTo(expected);
  }