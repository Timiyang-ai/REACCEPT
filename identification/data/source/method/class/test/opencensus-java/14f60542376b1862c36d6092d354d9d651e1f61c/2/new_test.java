  @Test
  public void createMetricKind() {
    assertThat(StackdriverExportUtils.createMetricKind(Type.CUMULATIVE_INT64))
        .isEqualTo(MetricKind.CUMULATIVE);
    assertThat(StackdriverExportUtils.createMetricKind(Type.SUMMARY))
        .isEqualTo(MetricKind.UNRECOGNIZED);
    assertThat(StackdriverExportUtils.createMetricKind(Type.GAUGE_INT64))
        .isEqualTo(MetricKind.GAUGE);
    assertThat(StackdriverExportUtils.createMetricKind(Type.GAUGE_DOUBLE))
        .isEqualTo(MetricKind.GAUGE);
  }