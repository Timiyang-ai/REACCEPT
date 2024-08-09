  @Test
  public void test_builder1() {
    DefaultSurfaceMetadata test = DefaultSurfaceMetadata.builder()
        .surfaceName(SURFACE_NAME.toString())
        .xValueType(ValueType.YEAR_FRACTION)
        .yValueType(ValueType.DISCOUNT_FACTOR)
        .zValueType(ValueType.ZERO_RATE)
        .dayCount(ACT_365F)
        .addInfo(DESCRIPTION, "Hello")
        .parameterMetadata(ImmutableList.of(ParameterMetadata.empty()))
        .build();
    assertThat(test.getSurfaceName()).isEqualTo(SURFACE_NAME);
    assertThat(test.getXValueType()).isEqualTo(ValueType.YEAR_FRACTION);
    assertThat(test.getYValueType()).isEqualTo(ValueType.DISCOUNT_FACTOR);
    assertThat(test.getZValueType()).isEqualTo(ValueType.ZERO_RATE);
    assertThat(test.getInfo(SurfaceInfoType.DAY_COUNT)).isEqualTo(ACT_365F);
    assertThat(test.findInfo(SurfaceInfoType.DAY_COUNT)).isEqualTo(Optional.of(ACT_365F));
    assertThat(test.getInfo(DESCRIPTION)).isEqualTo("Hello");
    assertThat(test.findInfo(DESCRIPTION)).isEqualTo(Optional.of("Hello"));
    assertThat(test.findInfo(SurfaceInfoType.of("Rubbish"))).isEqualTo(Optional.empty());
    assertThat(test.getParameterMetadata().isPresent()).isTrue();
    assertThat(test.getParameterMetadata().get()).containsExactly(ParameterMetadata.empty());
  }