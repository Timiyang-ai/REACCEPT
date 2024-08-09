  @Test
  public void test_presentValue() {
    SimpleRatesProvider prov = createProvider(NOTIONAL_EXCHANGE_REC_GBP);

    DiscountingNotionalExchangePricer test = DiscountingNotionalExchangePricer.DEFAULT;
    double calculated = test.presentValue(NOTIONAL_EXCHANGE_REC_GBP, prov);
    assertThat(calculated).isCloseTo(NOTIONAL_EXCHANGE_REC_GBP.getPaymentAmount().getAmount() * DISCOUNT_FACTOR, offset(0d));
  }