  @Test
  public void test_forecastValueSensitivity_ISDA() {
    SimpleRatesProvider prov = createProvider(RFRA);

    DiscountingFraProductPricer test = DiscountingFraProductPricer.DEFAULT;
    PointSensitivities sensitivity = test.forecastValueSensitivity(RFRA, prov);
    double eps = 1.e-7;
    double fdSense = forecastValueFwdSensitivity(RFRA, FORWARD_RATE, eps);

    ImmutableList<PointSensitivity> sensitivities = sensitivity.getSensitivities();
    assertThat(sensitivities).hasSize(1);
    IborRateSensitivity sensitivity0 = (IborRateSensitivity) sensitivities.get(0);
    assertThat(sensitivity0.getIndex()).isEqualTo(FRA.getIndex());
    assertThat(sensitivity0.getObservation().getFixingDate()).isEqualTo(FRA.getStartDate());
    assertThat(sensitivity0.getSensitivity()).isCloseTo(fdSense, offset(FRA.getNotional() * eps));

    // test via FraTrade
    DiscountingFraTradePricer testTrade = new DiscountingFraTradePricer(test);
    assertThat(testTrade.forecastValueSensitivity(RFRA_TRADE, prov)).isEqualTo(test.forecastValueSensitivity(RFRA, prov));
  }