  @Test
  public void truncateSetInclusiveTest() {
    double lower = 0.1;
    double upper = 2.5;

    DoubleArray set0 = DoubleArray.of(-0.2, 1.5, 2.9);
    DoubleArray exp0 = DoubleArray.of(0.1, 1.5, 2.5);
    DoubleArray res0 = DoublesScheduleGenerator.truncateSetInclusive(lower, upper, set0);
    assertThat(DoubleArrayMath.fuzzyEquals(exp0.toArray(), res0.toArray(), 0d)).isTrue();

    DoubleArray set1 = DoubleArray.of(-0.2, -0.1);
    DoubleArray exp1 = DoubleArray.of(0.1, 2.5);
    DoubleArray res1 = DoublesScheduleGenerator.truncateSetInclusive(lower, upper, set1);
    assertThat(DoubleArrayMath.fuzzyEquals(exp1.toArray(), res1.toArray(), 0d)).isTrue();

    DoubleArray set2 = DoubleArray.of(0.1 + 1.e-3, 1.5, 2.8);
    DoubleArray exp2 = DoubleArray.of(0.1, 1.5, 2.5);
    DoubleArray res2 = DoublesScheduleGenerator.truncateSetInclusive(lower, upper, set2);
    assertThat(DoubleArrayMath.fuzzyEquals(exp2.toArray(), res2.toArray(), 0d)).isTrue();

    DoubleArray set3 = DoubleArray.of(0., 1.5, 2.5 + 1.e-3);
    DoubleArray exp3 = DoubleArray.of(0.1, 1.5, 2.5);
    DoubleArray res3 = DoublesScheduleGenerator.truncateSetInclusive(lower, upper, set3);
    assertThat(DoubleArrayMath.fuzzyEquals(exp3.toArray(), res3.toArray(), 0d)).isTrue();

    DoubleArray set4 = DoubleArray.of(0.1 - 1.e-4, 1.5, 2.5 - 1.e-4);
    DoubleArray exp4 = DoubleArray.of(0.1, 1.5, 2.5);
    DoubleArray res4 = DoublesScheduleGenerator.truncateSetInclusive(lower, upper, set4);
    assertThat(DoubleArrayMath.fuzzyEquals(exp4.toArray(), res4.toArray(), 0d)).isTrue();

    DoubleArray set5 = DoubleArray.of(lower + 1.e-4, lower + 2.e-4);
    DoubleArray exp5 = DoubleArray.of(lower, lower + 1.e-3);
    DoubleArray res5 = DoublesScheduleGenerator.truncateSetInclusive(lower, lower + 1.e-3, set5);
    assertThat(DoubleArrayMath.fuzzyEquals(exp5.toArray(), res5.toArray(), 0d)).isTrue();
  }