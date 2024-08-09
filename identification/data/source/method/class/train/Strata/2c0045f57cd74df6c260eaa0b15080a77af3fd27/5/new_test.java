  @Test
  public void test_multipliedBy() {
    FxIndexSensitivity base = FxIndexSensitivity.of(GBP_USD_WM_OBS, USD, GBP, SENSITIVITY_VALUE);
    FxIndexSensitivity test = base.multipliedBy(2.4d);
    FxIndexSensitivity expected = FxIndexSensitivity.of(GBP_USD_WM_OBS, USD, GBP, SENSITIVITY_VALUE * 2.4d);
    assertThat(test).isEqualTo(expected);
  }