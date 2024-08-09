  @Test
  public void test_withCurrency() {
    IborFutureOptionSensitivity base = IborFutureOptionSensitivity.of(
        NAME, 12d, date(2015, 8, 28), 0.98, 0.99, GBP, 32d);
    assertThat(base.withCurrency(GBP)).isSameAs(base);

    IborFutureOptionSensitivity expected = IborFutureOptionSensitivity.of(
        NAME, 12d, date(2015, 8, 28), 0.98, 0.99, USD, 32d);
    IborFutureOptionSensitivity test = base.withCurrency(USD);
    assertThat(test).isEqualTo(expected);
  }