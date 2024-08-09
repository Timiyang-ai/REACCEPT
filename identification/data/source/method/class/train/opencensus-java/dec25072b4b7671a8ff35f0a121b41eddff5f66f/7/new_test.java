  @Test
  public void createValueType() {
    assertThat(StackdriverExportUtils.createValueType(Type.GAUGE_DOUBLE))
        .isEqualTo(MetricDescriptor.ValueType.DOUBLE);
    assertThat(StackdriverExportUtils.createValueType(Type.CUMULATIVE_INT64))
        .isEqualTo(MetricDescriptor.ValueType.INT64);
    assertThat(StackdriverExportUtils.createValueType(Type.GAUGE_INT64))
        .isEqualTo(MetricDescriptor.ValueType.INT64);
    assertThat(StackdriverExportUtils.createValueType(Type.CUMULATIVE_DOUBLE))
        .isEqualTo(MetricDescriptor.ValueType.DOUBLE);
    assertThat(StackdriverExportUtils.createValueType(Type.GAUGE_DISTRIBUTION))
        .isEqualTo(MetricDescriptor.ValueType.DISTRIBUTION);
    assertThat(StackdriverExportUtils.createValueType(Type.CUMULATIVE_DISTRIBUTION))
        .isEqualTo(MetricDescriptor.ValueType.DISTRIBUTION);
  }