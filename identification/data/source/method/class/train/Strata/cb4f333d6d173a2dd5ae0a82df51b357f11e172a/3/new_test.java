  @Test
  public void test_multipliedBy_currency() {
    UnitParameterSensitivity base = UnitParameterSensitivity.of(NAME1, METADATA1, VECTOR1);
    CurrencyParameterSensitivity test = base.multipliedBy(USD, FACTOR1);
    assertThat(test).isEqualTo(CurrencyParameterSensitivity.of(NAME1, METADATA1, USD, VECTOR1_FACTOR));
  }