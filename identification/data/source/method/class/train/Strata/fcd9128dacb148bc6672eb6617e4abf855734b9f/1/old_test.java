  @Test
  public void test_multipliedBy() {
    IborFutureOptionSensitivity base = IborFutureOptionSensitivity.of(
        NAME, 12d, date(2015, 8, 28), 0.98, 0.99, GBP, 32d);
    IborFutureOptionSensitivity expected = IborFutureOptionSensitivity.of(
        NAME, 12d, date(2015, 8, 28), 0.98, 0.99, GBP, 32d * 3.5d);
    IborFutureOptionSensitivity test = base.multipliedBy(3.5d);
    assertThat(test).isEqualTo(expected);
  }