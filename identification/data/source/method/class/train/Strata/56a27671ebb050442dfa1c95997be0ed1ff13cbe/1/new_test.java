  @Test
  public void test_withMetadata() {
    InterpolatedNodalCurve base = InterpolatedNodalCurve.of(METADATA, XVALUES, YVALUES, INTERPOLATOR);
    InterpolatedNodalCurve test = base.withMetadata(METADATA_ENTRIES);
    assertThat(test.getName()).isEqualTo(CURVE_NAME);
    assertThat(test.getParameterCount()).isEqualTo(SIZE);
    assertThat(test.getMetadata()).isEqualTo(METADATA_ENTRIES);
    assertThat(test.getXValues()).isEqualTo(XVALUES);
    assertThat(test.getYValues()).isEqualTo(YVALUES);
  }