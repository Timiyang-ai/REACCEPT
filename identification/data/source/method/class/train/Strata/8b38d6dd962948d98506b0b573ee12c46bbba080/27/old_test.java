  @Test
  public void test_convexityAdjustment() {
    double computed = PRICER.convexityAdjustment(FUTURE, RATE_PROVIDER, HW_PROVIDER);
    double priceHw = PRICER.price(FUTURE, RATE_PROVIDER, HW_PROVIDER);
    double priceDsc = PRICER_DSC.price(FUTURE, RATE_PROVIDER); // no convexity adjustment
    assertThat(priceDsc + computed).isCloseTo(priceHw, offset(TOL));
  }