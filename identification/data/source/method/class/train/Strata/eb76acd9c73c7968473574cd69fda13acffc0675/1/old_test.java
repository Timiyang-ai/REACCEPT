  @Test
  public void toObject() {
    assertThat(DoubleArrayMath.toObject(new double[] {})).isEqualTo(new Double[] {});
    assertThat(DoubleArrayMath.toObject(new double[] {1d, 2.5d})).isEqualTo(new Double[] {1d, 2.5d});
  }