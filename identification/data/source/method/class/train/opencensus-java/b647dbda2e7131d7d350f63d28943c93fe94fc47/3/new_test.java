  @Test
  public void createDescribableMetricFamilySamples() {
    assertThat(
            PrometheusExportUtils.createDescribableMetricFamilySamples(
                CUMULATIVE_METRIC_DESCRIPTOR, ""))
        .isEqualTo(
            new MetricFamilySamples(
                METRIC_NAME, Type.COUNTER, METRIC_DESCRIPTION, Collections.<Sample>emptyList()));
    assertThat(
            PrometheusExportUtils.createDescribableMetricFamilySamples(
                SUMMARY_METRIC_DESCRIPTOR, ""))
        .isEqualTo(
            new MetricFamilySamples(
                METRIC_NAME2, Type.SUMMARY, METRIC_DESCRIPTION, Collections.<Sample>emptyList()));
    assertThat(
            PrometheusExportUtils.createDescribableMetricFamilySamples(
                HISTOGRAM_METRIC_DESCRIPTOR, ""))
        .isEqualTo(
            new MetricFamilySamples(
                METRIC_NAME3, Type.HISTOGRAM, METRIC_DESCRIPTION, Collections.<Sample>emptyList()));
  }