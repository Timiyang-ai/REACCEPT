  @Test
  public void test_multipliedBy() {
    BondFutureOptionSensitivity base = BondFutureOptionSensitivity.of(
        NAME, OPTION_EXPIRY, FUTURE_EXPIRY, STRIKE_PRICE, FUTURE_PRICE, GBP, SENSITIVITY);
    BondFutureOptionSensitivity expected = BondFutureOptionSensitivity.of(
        NAME, OPTION_EXPIRY, FUTURE_EXPIRY, STRIKE_PRICE, FUTURE_PRICE, GBP, SENSITIVITY * 3.5d);
    BondFutureOptionSensitivity test = base.multipliedBy(3.5d);
    assertThat(test).isEqualTo(expected);
  }