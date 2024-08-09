  @Test
  public void test_forecastValue_ISDA() {
    SimpleRatesProvider prov = createProvider(RFRA);

    double fixedRate = FRA.getFixedRate();
    double yearFraction = RFRA.getYearFraction();
    double notional = RFRA.getNotional();
    double expected = notional * yearFraction * (FORWARD_RATE - fixedRate) / (1.0 + yearFraction * FORWARD_RATE);

    DiscountingFraProductPricer test = DiscountingFraProductPricer.DEFAULT;
    CurrencyAmount computed = test.forecastValue(RFRA, prov);
    assertThat(computed.getAmount()).isCloseTo(expected, offset(TOLERANCE));

    // test via FraTrade
    DiscountingFraTradePricer testTrade = new DiscountingFraTradePricer(test);
    assertThat(testTrade.forecastValue(RFRA_TRADE, prov)).isEqualTo(test.forecastValue(RFRA, prov));
  }