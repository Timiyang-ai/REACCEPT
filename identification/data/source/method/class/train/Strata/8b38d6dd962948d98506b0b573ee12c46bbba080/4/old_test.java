  @Test
  public void test_price() {
    double computed = PRICER.price(FUTURE, RATE_PROVIDER, HW_PROVIDER);
    LocalDate start = FUTURE.getIborRate().getObservation().getEffectiveDate();
    LocalDate end = FUTURE.getIborRate().getObservation().getMaturityDate();
    double fixingYearFraction = FUTURE.getIborRate().getObservation().getYearFraction();
    double convexity = HW_PROVIDER.futuresConvexityFactor(FUTURE.getLastTradeDate(), start, end);
    double forward = RATE_PROVIDER.iborIndexRates(EUR_EURIBOR_3M).rate(FUTURE.getIborRate().getObservation());
    double expected = 1d - convexity * forward + (1d - convexity) / fixingYearFraction;
    assertThat(computed).isCloseTo(expected, offset(TOL));
  }