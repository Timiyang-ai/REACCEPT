  @Test
  public void test_price() {
    double computed = TRADE_PRICER.price(FUTURE_TRADE, PROVIDER);
    double expected = PRODUCT_PRICER.price(FUTURE, PROVIDER);
    assertThat(computed).isCloseTo(expected, offset(TOL));
  }