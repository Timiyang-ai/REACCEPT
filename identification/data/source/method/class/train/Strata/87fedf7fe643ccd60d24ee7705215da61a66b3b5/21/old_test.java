  @Test
  public void test_toFxForwardSensitivity() {
    FxIndexSensitivity test = FxIndexSensitivity.of(GBP_USD_WM_OBS, GBP, USD, SENSITIVITY_VALUE);
    LocalDate maturityDate = GBP_USD_WM.calculateMaturityFromFixing(FIXING_DATE, REF_DATA);
    FxForwardSensitivity expected =
        FxForwardSensitivity.of(CurrencyPair.of(GBP, USD), GBP, maturityDate, USD, SENSITIVITY_VALUE);
    assertThat(test.toFxForwardSensitivity()).isEqualTo(expected);
  }