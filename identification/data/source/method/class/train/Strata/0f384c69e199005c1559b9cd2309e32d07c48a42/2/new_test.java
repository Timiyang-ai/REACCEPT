  @Test
  public void test_get() {
    double[][] base = {{1d, 2d}, {3d, 4d}, {5d, 6d}};
    DoubleMatrix test = DoubleMatrix.copyOf(base);
    assertThat(test.get(0, 0)).isEqualTo(1d);
    assertThat(test.get(2, 1)).isEqualTo(6d);
    assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> test.get(-1, 0));
    assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> test.get(0, 4));
  }