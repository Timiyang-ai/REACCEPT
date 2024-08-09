  @Test
  public void test_withMarketDataNames() {
    CurveSensitivities base = sut();
    CurveSensitivities test = base.withMarketDataNames(name -> NAME2);
    assertThat(base.getTypedSensitivities().get(ZERO_RATE_DELTA).getSensitivities().get(0).getMarketDataName()).isEqualTo(NAME1);
    assertThat(test.getTypedSensitivities().get(ZERO_RATE_DELTA).getSensitivities().get(0).getMarketDataName()).isEqualTo(NAME2);
  }