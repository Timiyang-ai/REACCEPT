  @Test
  public void test_withCurrency_same() {
    FxForwardSensitivity base = FxForwardSensitivity.of(CURRENCY_PAIR, GBP, REFERENCE_DATE, SENSITIVITY);
    FxForwardSensitivity test = base.withCurrency(EUR);
    assertThat(test).isEqualTo(base);
  }