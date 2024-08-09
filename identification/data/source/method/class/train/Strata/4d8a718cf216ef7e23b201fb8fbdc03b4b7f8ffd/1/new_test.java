  @Test
  public void test_combineReduce() {
    DoubleArray test1 = DoubleArray.of(1d, 2d, 3d);
    DoubleArray test2 = DoubleArray.of(0.5d, 0.6d, 0.7d);
    assertThat(test1.combineReduce(test2, (r, a, b) -> r + a * b)).isEqualTo(0.5d + 2d * 0.6d + 3d * 0.7d);
    assertThatIllegalArgumentException()
        .isThrownBy(() -> test1.combineReduce(DoubleArray.EMPTY, (r, a, b) -> r + a * b));
  }