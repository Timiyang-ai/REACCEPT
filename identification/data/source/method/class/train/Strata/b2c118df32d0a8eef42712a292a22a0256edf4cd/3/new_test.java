  @Test
  public void test_multipliedBy() {
    FxOptionSensitivity base = FxOptionSensitivity.of(NAME, PAIR, EXPIRY, STRIKE, FORWARD, GBP, SENSI_VALUE);
    double factor = 5.2d;
    FxOptionSensitivity expected = FxOptionSensitivity.of(
        NAME, PAIR, EXPIRY, STRIKE, FORWARD, GBP, SENSI_VALUE * factor);
    FxOptionSensitivity test = base.multipliedBy(factor);
    assertThat(test).isEqualTo(expected);
  }