  @Test
  public void test_concat_varargs() {
    DoubleArray test1 = DoubleArray.of(1d, 2d, 3d);
    assertContent(test1.concat(0.5d, 0.6d, 0.7d), 1d, 2d, 3d, 0.5d, 0.6d, 0.7d);
    assertContent(test1.concat(new double[] {0.5d, 0.6d, 0.7d}), 1d, 2d, 3d, 0.5d, 0.6d, 0.7d);
    assertContent(test1.concat(EMPTY_DOUBLE_ARRAY), 1d, 2d, 3d);
    assertContent(DoubleArray.EMPTY.concat(new double[] {1d, 2d, 3d}), 1d, 2d, 3d);
  }