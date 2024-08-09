  @Test
  public void test_forwardFxRatePointSensitivity() {
    PointSensitivityBuilder computed = PRICER.forwardFxRatePointSensitivity(FWD, PROVIDER);
    FxForwardSensitivity expected = FxForwardSensitivity.of(CurrencyPair.of(USD, KRW), USD, FWD.getPaymentDate(), 1d);
    assertThat(computed).isEqualTo(expected);
  }