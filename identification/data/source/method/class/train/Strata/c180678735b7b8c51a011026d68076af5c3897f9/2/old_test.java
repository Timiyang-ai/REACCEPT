  @Test
  public void absolute() {
    ParallelShiftedCurve test = ParallelShiftedCurve.absolute(CONSTANT_CURVE, 1d);
    assertThat(test.getUnderlyingCurve()).isEqualTo(CONSTANT_CURVE);
    assertThat(test.getShiftType()).isEqualTo(ShiftType.ABSOLUTE);
    assertThat(test.getShiftAmount()).isEqualTo(1d);
    assertThat(test.getMetadata()).isEqualTo(METADATA);
    assertThat(test.getName()).isEqualTo(METADATA.getCurveName());
    assertThat(test.getParameterCount()).isEqualTo(2);
    assertThat(test.getParameter(0)).isEqualTo(3d);
    assertThat(test.getParameter(1)).isEqualTo(1d);
    assertThat(test.getParameterMetadata(0)).isEqualTo(ParameterMetadata.empty());
    assertThat(test.getParameterMetadata(1)).isEqualTo(LabelParameterMetadata.of("AbsoluteShift"));
    assertThat(test.withParameter(0, 5d)).isEqualTo(ParallelShiftedCurve.absolute(CONSTANT_CURVE2, 1d));
    assertThat(test.withParameter(1, 0.5d)).isEqualTo(ParallelShiftedCurve.absolute(CONSTANT_CURVE, 0.5d));
    assertThat(test.withPerturbation((i, v, m) -> v + 2d)).isEqualTo(ParallelShiftedCurve.absolute(CONSTANT_CURVE2, 3d));
    assertThat(test.yValue(0)).isEqualTo(4d);
    assertThat(test.yValue(1)).isEqualTo(4d);
  }