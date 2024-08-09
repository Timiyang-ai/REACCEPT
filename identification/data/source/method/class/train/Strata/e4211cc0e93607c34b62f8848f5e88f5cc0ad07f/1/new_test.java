  @Test
  public void test_combine() {
    DoubleBinaryOperator operator = (a, b) -> a / b;
    assertThat(DoubleArrayMath.combine(ARRAY_1_2, ARRAY_3_4, operator)).contains(1d / 3d, 2d / 4d);
    assertThatIllegalArgumentException().isThrownBy(() -> DoubleArrayMath.combine(ARRAY_1_2, ARRAY_3, operator));
  }