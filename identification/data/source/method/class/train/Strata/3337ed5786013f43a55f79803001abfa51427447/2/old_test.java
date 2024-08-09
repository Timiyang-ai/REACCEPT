  @Test
  public void test_withParameterMetadatas() {
    CurveSensitivities base = sut();
    CurveSensitivities test = base.withParameterMetadatas(md -> TENOR_MD_1Y);
    CurrencyParameterSensitivity testSens = test.getTypedSensitivities().get(ZERO_RATE_DELTA).getSensitivities().get(0);
    assertThat(testSens.getParameterMetadata()).containsExactly(TENOR_MD_1Y);
    assertThat(testSens.getSensitivity()).isEqualTo(DoubleArray.of(723));
  }