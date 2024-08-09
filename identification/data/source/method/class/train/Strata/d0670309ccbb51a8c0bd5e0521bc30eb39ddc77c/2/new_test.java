  @Test
  public void test_withZValues() {
    InterpolatedNodalSurface base = InterpolatedNodalSurface.of(METADATA, XVALUES, YVALUES, ZVALUES, INTERPOLATOR);
    InterpolatedNodalSurface test = base.withZValues(ZVALUES_BUMPED);
    assertThat(test.getName()).isEqualTo(SURFACE_NAME);
    assertThat(test.getParameterCount()).isEqualTo(SIZE);
    assertThat(test.getMetadata()).isEqualTo(METADATA);
    assertThat(test.getXValues()).isEqualTo(XVALUES);
    assertThat(test.getYValues()).isEqualTo(YVALUES);
    assertThat(test.getZValues()).isEqualTo(ZVALUES_BUMPED);
  }