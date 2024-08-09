  @Test
  public void ofBlackVolatility() {
    double shift = 0.0075;
    RawOptionData test =
        RawOptionData.ofBlackVolatility(EXPIRIES, STRIKES, ValueType.STRIKE, DATA_SPARSE, shift);
    assertThat(test.getStrikes()).isEqualTo(STRIKES);
    assertThat(test.getStrikeType()).isEqualTo(ValueType.STRIKE);
    assertThat(test.getData()).isEqualTo(DATA_SPARSE);
    assertThat(test.getDataType()).isEqualTo(ValueType.BLACK_VOLATILITY);
    assertThat(test.getShift()).isEqualTo(OptionalDouble.of(shift));
    assertThat(test.getError().isPresent()).isFalse();
  }