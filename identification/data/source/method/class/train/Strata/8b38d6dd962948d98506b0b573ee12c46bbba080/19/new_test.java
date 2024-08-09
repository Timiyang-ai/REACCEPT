  @Test
  public void test_presentValue() {
    CurrencyAmount computed = PRICER.presentValue(FUTURE_TRADE, RATE_PROVIDER, HW_PROVIDER, LAST_PRICE);
    double price = PRICER_PRODUCT.price(FUTURE, RATE_PROVIDER, HW_PROVIDER);
    double expected = (price - LAST_PRICE) * FUTURE.getAccrualFactor() * NOTIONAL * QUANTITY;
    assertThat(computed.getCurrency()).isEqualTo(EUR);
    assertThat(computed.getAmount()).isCloseTo(expected, offset(TOL * NOTIONAL * QUANTITY));
  }