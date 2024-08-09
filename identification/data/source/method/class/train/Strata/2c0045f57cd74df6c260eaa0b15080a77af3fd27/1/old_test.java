  @Test
  public void test_withCurrency() {
    FxIndexSensitivity base = FxIndexSensitivity.of(GBP_USD_WM_OBS, USD, GBP, SENSITIVITY_VALUE);
    assertThat(base.withCurrency(GBP)).isSameAs(base);

    FxIndexSensitivity test = base.withCurrency(JPY);
    FxIndexSensitivity expected = FxIndexSensitivity.of(GBP_USD_WM_OBS, USD, JPY, SENSITIVITY_VALUE);
    assertThat(test).isEqualTo(expected);
  }