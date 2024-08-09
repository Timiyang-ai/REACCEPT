  @Test
  public void test_withSensitivity() {
    CurrencyParameterSensitivity base = CurrencyParameterSensitivity.of(NAME1, METADATA_USD1, USD, VECTOR_USD1);
    CurrencyParameterSensitivity test = base.withSensitivity(VECTOR_USD_FACTOR);
    assertThat(test).isEqualTo(CurrencyParameterSensitivity.of(NAME1, METADATA_USD1, USD, VECTOR_USD_FACTOR));
    assertThatIllegalArgumentException()
        .isThrownBy(() -> base.withSensitivity(DoubleArray.of(1d)));
  }