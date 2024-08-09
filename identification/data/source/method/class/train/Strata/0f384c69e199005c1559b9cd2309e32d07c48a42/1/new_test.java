  @Test
  public void test_toString() {
    DoubleMatrix test = DoubleMatrix.copyOf(new double[][] {{1d, 2d}, {3d, 4d}, {5d, 6d}});
    assertThat(test.toString()).isEqualTo("1.0 2.0\n3.0 4.0\n5.0 6.0\n");
  }