  @Test
  public void test_multipliedBy() {
    FxForwardSensitivity base = FxForwardSensitivity.of(CURRENCY_PAIR, GBP, REFERENCE_DATE, SENSITIVITY);
    FxForwardSensitivity test = base.multipliedBy(2.4d);
    FxForwardSensitivity expected = FxForwardSensitivity.of(CURRENCY_PAIR, GBP, REFERENCE_DATE, SENSITIVITY * 2.4d);
    assertThat(test).isEqualTo(expected);
  }