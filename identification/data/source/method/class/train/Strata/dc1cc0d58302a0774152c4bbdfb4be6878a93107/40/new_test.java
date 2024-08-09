  @Test
  public void test_presentValue() {
    SimpleRatesProvider prov = createProvider(FX_RESET_NOTIONAL_EXCHANGE_REC_USD);

    DiscountingFxResetNotionalExchangePricer test = new DiscountingFxResetNotionalExchangePricer();
    double calculated = test.presentValue(FX_RESET_NOTIONAL_EXCHANGE_REC_USD, prov);
    assertThat(calculated).isCloseTo(FX_RESET_NOTIONAL_EXCHANGE_REC_USD.getNotional() * FX_RATE * DISCOUNT_FACTOR, offset(0d));
  }