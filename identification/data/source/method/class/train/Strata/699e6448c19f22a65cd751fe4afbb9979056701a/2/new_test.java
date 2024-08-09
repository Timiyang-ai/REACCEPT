  @Test
  public void test_presentValueSensitivity() {
    SimpleRatesProvider prov = createProvider(VAL_DATE);

    PointSensitivities point = PRICER.presentValueSensitivity(PERIOD, prov).build();
    double relativeYearFraction = DAY_COUNT.relativeYearFraction(VAL_DATE, PAYMENT_DATE);
    double expected = -DISCOUNT_FACTOR * relativeYearFraction * AMOUNT_1000;
    ZeroRateSensitivity actual = (ZeroRateSensitivity) point.getSensitivities().get(0);
    assertThat(actual.getCurrency()).isEqualTo(GBP);
    assertThat(actual.getCurveCurrency()).isEqualTo(GBP);
    assertThat(actual.getYearFraction()).isEqualTo(relativeYearFraction);
    assertThat(actual.getSensitivity()).isCloseTo(expected, offset(AMOUNT_1000 * TOLERANCE_PV));
  }