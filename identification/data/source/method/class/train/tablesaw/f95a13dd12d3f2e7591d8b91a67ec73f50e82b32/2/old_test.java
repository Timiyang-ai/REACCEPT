  @Test
  public void fillMissing_defaultValue() {
    DoubleColumn col1 =
        DoubleColumn.create(
            "col1",
            new double[] {
              0.0,
              1.0,
              DoubleColumnType.missingValueIndicator(),
              2.0,
              DoubleColumnType.missingValueIndicator()
            });
    DoubleColumn expected = DoubleColumn.create("expected", new double[] {0.0, 1.0, 7.0, 2.0, 7.0});
    assertArrayEquals(
        expected.asDoubleArray(), col1.set(col1.isMissing(), 7.0).asDoubleArray(), 0.0001);
  }