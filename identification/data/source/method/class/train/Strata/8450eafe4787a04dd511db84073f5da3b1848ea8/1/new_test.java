  @Test
  public void test_bind() {
    DoubleArray xValues = DoubleArray.of(1, 2, 3);
    DoubleArray yValues = DoubleArray.of(2, 4, 5);
    BoundCurveInterpolator bound = LINEAR.bind(xValues, yValues, CurveExtrapolators.FLAT, CurveExtrapolators.FLAT);
    assertThat(bound.interpolate(0.5)).isCloseTo(2d, offset(0d));
    assertThat(bound.interpolate(1)).isCloseTo(2d, offset(0d));
    assertThat(bound.interpolate(1.5)).isCloseTo(3d, offset(0d));
    assertThat(bound.interpolate(2)).isCloseTo(4d, offset(0d));
    assertThat(bound.interpolate(2.5)).isCloseTo(4.5d, offset(0d));
    assertThat(bound.interpolate(3)).isCloseTo(5d, offset(0d));
    assertThat(bound.interpolate(3.5)).isCloseTo(5d, offset(0d));
    // coverage
    assertThat(bound.parameterSensitivity(0.5).size()).isEqualTo(3);
    assertThat(bound.parameterSensitivity(2).size()).isEqualTo(3);
    assertThat(bound.parameterSensitivity(3.5).size()).isEqualTo(3);
    assertThat(bound.firstDerivative(0.5)).isCloseTo(0d, offset(0d));
    assertThat(bound.firstDerivative(2) != 0d).isTrue();
    assertThat(bound.firstDerivative(3.5)).isCloseTo(0d, offset(0d));
    assertThat(bound.toString()).isNotNull();
  }