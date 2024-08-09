  @Test
  public void getIntegrationsPointsTest() {
    double start = 0.1;
    double end = 2.;

    DoubleArray setA0 = DoubleArray.of(0.5, 0.9, 1.4);
    DoubleArray setB0 = DoubleArray.of(0.3, 0.4, 1.5, 1.6);
    DoubleArray exp0 = DoubleArray.of(0.1, 0.3, 0.4, 0.5, 0.9, 1.4, 1.5, 1.6, 2.);
    DoubleArray res0 = DoublesScheduleGenerator.getIntegrationsPoints(start, end, setA0, setB0);
    assertThat(DoubleArrayMath.fuzzyEquals(exp0.toArray(), res0.toArray(), 0d)).isTrue();

    DoubleArray setA1 = DoubleArray.of(0.5, 0.9, 1.4);
    DoubleArray setB1 = DoubleArray.of(0.3, 0.4, 0.5, 1.5, 1.6);
    DoubleArray exp1 = DoubleArray.of(0.1, 0.3, 0.4, 0.5, 0.9, 1.4, 1.5, 1.6, 2.);
    DoubleArray res1 = DoublesScheduleGenerator.getIntegrationsPoints(start, end, setA1, setB1);
    assertThat(DoubleArrayMath.fuzzyEquals(exp1.toArray(), res1.toArray(), 0d)).isTrue();

    /*
     * different(temp[pos], end) == false
     */
    DoubleArray setA2 = DoubleArray.of(0.2, 0.9, 1.4);
    DoubleArray setB2 = DoubleArray.of(0.3, 0.4, 0.5, 1.5, 2. - 1.e-3);
    DoubleArray exp2 = DoubleArray.of(0.1, 0.2, 0.3, 0.4, 0.5, 0.9, 1.4, 1.5, 2.);
    DoubleArray res2 = DoublesScheduleGenerator.getIntegrationsPoints(start, end, setA2, setB2);
    assertThat(DoubleArrayMath.fuzzyEquals(exp2.toArray(), res2.toArray(), 0d)).isTrue();

    DoubleArray setA3 = DoubleArray.of(0.05, 0.07);
    DoubleArray setB3 = DoubleArray.of(0.03, 0.04);
    DoubleArray exp3 = DoubleArray.of(0.1, 2.);
    DoubleArray res3 = DoublesScheduleGenerator.getIntegrationsPoints(start, end, setA3, setB3);
    assertThat(DoubleArrayMath.fuzzyEquals(exp3.toArray(), res3.toArray(), 0d)).isTrue();

    DoubleArray setA4 = DoubleArray.of(2.2, 2.7);
    DoubleArray setB4 = DoubleArray.of(2.3, 2.4);
    DoubleArray exp4 = DoubleArray.of(0.1, 2.);
    DoubleArray res4 = DoublesScheduleGenerator.getIntegrationsPoints(start, end, setA4, setB4);
    assertThat(DoubleArrayMath.fuzzyEquals(exp4.toArray(), res4.toArray(), 0d)).isTrue();

    DoubleArray setA5 = DoubleArray.of(-0.5, 0., 1.2);
    DoubleArray setB5 = DoubleArray.of(-0.2, -0., 1.2);
    DoubleArray exp5 = DoubleArray.of(-0.3, -0.2, 0., 1.2, 2.);
    DoubleArray res5 = DoublesScheduleGenerator.getIntegrationsPoints(-0.3, end, setA5, setB5);
    assertThat(DoubleArrayMath.fuzzyEquals(exp5.toArray(), res5.toArray(), 0d)).isTrue();
  }