  @Test
  public void test_withParameterMetadata() {
    DefaultSurfaceMetadata test = DefaultSurfaceMetadata.of(SURFACE_NAME)
        .withParameterMetadata(ImmutableList.of(ParameterMetadata.empty()));
    assertThat(test.getSurfaceName()).isEqualTo(SURFACE_NAME);
    assertThat(test.getXValueType()).isEqualTo(ValueType.UNKNOWN);
    assertThat(test.getYValueType()).isEqualTo(ValueType.UNKNOWN);
    assertThat(test.getZValueType()).isEqualTo(ValueType.UNKNOWN);
    assertThat(test.getParameterMetadata().isPresent()).isTrue();
    assertThat(test.getParameterMetadata().get()).containsExactly(ParameterMetadata.empty());
  }