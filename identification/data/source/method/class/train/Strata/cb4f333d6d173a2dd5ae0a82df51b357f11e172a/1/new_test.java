  @Test
  public void test_withSensitivity() {
    UnitParameterSensitivity base = UnitParameterSensitivity.of(NAME1, METADATA1, VECTOR1);
    UnitParameterSensitivity test = base.withSensitivity(VECTOR1_FACTOR);
    assertThat(test).isEqualTo(UnitParameterSensitivity.of(NAME1, METADATA1, VECTOR1_FACTOR));
    assertThatIllegalArgumentException()
        .isThrownBy(() -> base.withSensitivity(DoubleArray.of(1d)));
  }