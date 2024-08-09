  @Test
  public void test_withSensitivity() {
    CrossGammaParameterSensitivity base = CrossGammaParameterSensitivity.of(NAME1, METADATA_USD1, USD, MATRIX_USD1);
    CrossGammaParameterSensitivity test = base.withSensitivity(MATRIX_USD_FACTOR);
    assertThat(test).isEqualTo(CrossGammaParameterSensitivity.of(NAME1, METADATA_USD1, USD, MATRIX_USD_FACTOR));
    assertThatIllegalArgumentException()
        .isThrownBy(() -> base.withSensitivity(DoubleMatrix.of(1, 1, 1d)));
  }