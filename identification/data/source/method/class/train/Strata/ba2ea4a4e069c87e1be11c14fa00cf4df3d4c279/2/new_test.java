  @Test
  public void test_withCurrency() {
    BondFutureOptionSensitivity base = BondFutureOptionSensitivity.of(
        NAME, OPTION_EXPIRY, FUTURE_EXPIRY, STRIKE_PRICE, FUTURE_PRICE, GBP, SENSITIVITY);
    assertThat(base.withCurrency(GBP)).isSameAs(base);
    BondFutureOptionSensitivity expected = BondFutureOptionSensitivity.of(
        NAME, OPTION_EXPIRY, FUTURE_EXPIRY, STRIKE_PRICE, FUTURE_PRICE, USD, SENSITIVITY);
    BondFutureOptionSensitivity test = base.withCurrency(USD);
    assertThat(test).isEqualTo(expected);
  }