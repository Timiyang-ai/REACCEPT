  @Test
  public void test_presentValue_ISDA() {
    SimpleRatesProvider prov = createProvider(RFRA);

    DiscountingFraProductPricer test = DiscountingFraProductPricer.DEFAULT;
    CurrencyAmount pvComputed = test.presentValue(RFRA, prov);
    CurrencyAmount pvExpected = test.forecastValue(RFRA, prov).multipliedBy(DISCOUNT_FACTOR);
    assertThat(pvComputed.getAmount()).isCloseTo(pvExpected.getAmount(), offset(TOLERANCE));

    // test via FraTrade
    DiscountingFraTradePricer testTrade = new DiscountingFraTradePricer(test);
    assertThat(testTrade.presentValue(RFRA_TRADE, prov)).isEqualTo(test.presentValue(RFRA, prov));
  }