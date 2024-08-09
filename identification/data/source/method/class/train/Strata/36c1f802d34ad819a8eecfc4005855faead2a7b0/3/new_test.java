  @Test
  public void test_presentValueSensitivity_provider() {
    PointSensitivities point = PRICER.presentValueSensitivity(PAYMENT, PROVIDER).build();
    double relativeYearFraction = ACT_365F.relativeYearFraction(VAL_DATE_2014_01_22, PAYMENT_DATE);
    double expected = -DF * relativeYearFraction * NOTIONAL_USD;
    ZeroRateSensitivity actual = (ZeroRateSensitivity) point.getSensitivities().get(0);
    assertThat(actual.getCurrency()).isEqualTo(USD);
    assertThat(actual.getCurveCurrency()).isEqualTo(USD);
    assertThat(actual.getYearFraction()).isEqualTo(relativeYearFraction);
    assertThat(actual.getSensitivity()).isCloseTo(expected, offset(NOTIONAL_USD * TOL));
  }