  @Test
  public void test_combine() {
    CurrencyParameterSensitivity base1 = CurrencyParameterSensitivity.of(NAME1, METADATA_USD1, USD, VECTOR_USD1);
    CurrencyParameterSensitivity base2 = CurrencyParameterSensitivity.of(NAME2, METADATA_USD2, USD, VECTOR_USD2);
    CurrencyParameterSensitivity test = CurrencyParameterSensitivity.combine(NAME_COMBINED, base1, base2);
    assertThat(test.getMarketDataName()).isEqualTo(NAME_COMBINED);
    assertThat(test.getParameterCount()).isEqualTo(VECTOR_USD_COMBINED.size());
    assertThat(test.getParameterMetadata()).isEqualTo(METADATA_COMBINED);
    assertThat(test.getParameterMetadata(0)).isEqualTo(METADATA_COMBINED.get(0));
    assertThat(test.getSensitivity()).isEqualTo(VECTOR_USD_COMBINED);
    assertThat(test.getParameterSplit()).isEqualTo(Optional.of(PARAM_SPLIT));
  }