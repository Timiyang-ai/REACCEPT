  @Test
  public void test_presentValueWithSpread() {
    double computed = PRICER.presentValueWithSpread(
        PAYMENT_PERIOD, ISSUER_CURVE, Z_SPREAD, PERIODIC, PERIOD_PER_YEAR);
    double expected = FIXED_RATE * NOTIONAL * YEAR_FRACTION *
        DSC_FACTORS.discountFactorWithSpread(END_ADJUSTED, Z_SPREAD, PERIODIC, PERIOD_PER_YEAR);
    assertThat(computed).isEqualTo(expected);
  }