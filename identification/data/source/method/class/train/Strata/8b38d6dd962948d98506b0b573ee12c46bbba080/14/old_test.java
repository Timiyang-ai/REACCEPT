  @Test
  public void test_price() {
    double computed = PRICER.price(FUTURE_TRADE, RATE_PROVIDER, HW_PROVIDER);
    double expected = PRICER_PRODUCT.price(FUTURE, RATE_PROVIDER, HW_PROVIDER);
    assertThat(computed).isCloseTo(expected, offset(TOL));
  }