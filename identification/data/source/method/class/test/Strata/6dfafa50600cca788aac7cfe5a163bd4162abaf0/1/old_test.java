  @Test
  public void test_presentValue() {
    CurrencyAmount computed = TRADE_PRICER.presentValue(FUTURE_TRADE, PROVIDER, LASTMARG_PRICE);
    double expected = QUANTITY * NOTIONAL * (PRODUCT_PRICER.price(FUTURE, PROVIDER) - TRADE_PRICE);
    assertThat(computed.getCurrency()).isEqualTo(USD);
    assertThat(computed.getAmount()).isCloseTo(expected, offset(QUANTITY * NOTIONAL * TOL));
  }