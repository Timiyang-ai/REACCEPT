  @Test
  public void test_forecastValue() {
    SimpleRatesProvider prov = createProvider(NOTIONAL_EXCHANGE_REC_GBP);

    DiscountingNotionalExchangePricer test = DiscountingNotionalExchangePricer.DEFAULT;
    double calculated = test.forecastValue(NOTIONAL_EXCHANGE_REC_GBP, prov);
    assertThat(calculated).isCloseTo(NOTIONAL_EXCHANGE_REC_GBP.getPaymentAmount().getAmount(), offset(0d));
  }