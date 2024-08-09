  @Test
  public void test_diagonal() {
    CrossGammaParameterSensitivity base = CrossGammaParameterSensitivity.of(NAME1, METADATA_USD1, USD, MATRIX_USD1);
    CurrencyParameterSensitivity test = base.diagonal();
    DoubleArray value = DoubleArray.of(MATRIX_USD1.get(0, 0), MATRIX_USD1.get(1, 1));
    assertThat(test).isEqualTo(CurrencyParameterSensitivity.of(NAME1, METADATA_USD1, USD, value));
  }