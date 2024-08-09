  @Test
  public void test_presentValue() {
    double currentPrice = 0.995;
    double referencePrice = 0.9925;
    double currentPriceIndex = PRICER_PRODUCT.marginIndex(FUTURE_TRADE.getProduct(), currentPrice);
    double referencePriceIndex = PRICER_PRODUCT.marginIndex(FUTURE_TRADE.getProduct(), referencePrice);
    double presentValueExpected = (currentPriceIndex - referencePriceIndex) * FUTURE_TRADE.getQuantity();
    CurrencyAmount presentValueComputed = PRICER_TRADE.presentValue(FUTURE_TRADE, currentPrice, referencePrice);
    assertThat(presentValueComputed.getAmount()).isCloseTo(presentValueExpected, offset(TOLERANCE_PV));
  }