  @Test
  public void test_presentValueWithZSpread_continuous() {
    CurrencyAmount computedTrade = TRADE_PRICER.presentValueWithZSpread(
        TRADE, PROVIDER, Z_SPREAD, CONTINUOUS, 0);
    CurrencyAmount computedProduct =
        PRODUCT_PRICER.presentValueWithZSpread(PRODUCT, PROVIDER, Z_SPREAD, CONTINUOUS, 0, SETTLEMENT);
    CurrencyAmount pvPayment =
        PRICER_NOMINAL.presentValue(UPFRONT_PAYMENT, ZeroRateDiscountFactors.of(EUR, VAL_DATE, CURVE_REPO));
    assertThat(computedTrade.getAmount()).isCloseTo(computedProduct.multipliedBy(QUANTITY).plus(pvPayment).getAmount(), offset(NOTIONAL * QUANTITY * TOL));
  }