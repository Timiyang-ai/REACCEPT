  @Test
  public void test_presentValue() {
    CurrencyAmount computedTrade = TRADE_PRICER.presentValue(TRADE, PROVIDER);
    CurrencyAmount computedProduct = PRODUCT_PRICER.presentValue(PRODUCT, PROVIDER, SETTLEMENT);
    CurrencyAmount pvPayment =
        PRICER_NOMINAL.presentValue(UPFRONT_PAYMENT, ZeroRateDiscountFactors.of(EUR, VAL_DATE, CURVE_REPO));
    assertThat(computedTrade.getAmount()).isCloseTo(computedProduct.multipliedBy(QUANTITY).plus(pvPayment).getAmount(), offset(NOTIONAL * QUANTITY * TOL));
  }