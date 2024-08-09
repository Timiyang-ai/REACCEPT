  @Test
  public void test_parRate() {
    double computed = PRICER.parRate(FUTURE, RATE_PROVIDER, HW_PROVIDER);
    double price = PRICER.price(FUTURE, RATE_PROVIDER, HW_PROVIDER);
    assertThat(computed).isCloseTo(1d - price, offset(TOL));
  }