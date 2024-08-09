  @Test
  public void createMetricFamilySamples() {
    assertThat(PrometheusExportUtils.createMetricFamilySamples(LONG_METRIC, ""))
        .isEqualTo(
            new MetricFamilySamples(
                METRIC_NAME,
                Type.COUNTER,
                METRIC_DESCRIPTION,
                Collections.singletonList(
                    new Sample(
                        METRIC_NAME,
                        Arrays.asList("k1", "k2"),
                        Arrays.asList("v1", "v2"),
                        123456789))));
    assertThat(PrometheusExportUtils.createMetricFamilySamples(SUMMARY_METRIC, ""))
        .isEqualTo(
            new MetricFamilySamples(
                METRIC_NAME2,
                Type.SUMMARY,
                METRIC_DESCRIPTION,
                Arrays.asList(
                    new Sample(
                        METRIC_NAME2 + "_count",
                        Collections.singletonList("k_3"),
                        Collections.singletonList("v1"),
                        22),
                    new Sample(
                        METRIC_NAME2 + "_sum",
                        Collections.singletonList("k_3"),
                        Collections.singletonList("v1"),
                        74.8),
                    new Sample(
                        METRIC_NAME2,
                        Arrays.asList("k_3", LABEL_NAME_QUANTILE),
                        Arrays.asList("v1", "0.99"),
                        10.2))));
    assertThat(PrometheusExportUtils.createMetricFamilySamples(DISTRIBUTION_METRIC, ""))
        .isEqualTo(
            new MetricFamilySamples(
                METRIC_NAME3,
                Type.HISTOGRAM,
                METRIC_DESCRIPTION,
                Arrays.asList(
                    new Sample(
                        METRIC_NAME3 + "_bucket",
                        Arrays.asList("k1", "le"),
                        Arrays.asList("v-3", "1.0"),
                        0),
                    new Sample(
                        METRIC_NAME3 + "_bucket",
                        Arrays.asList("k1", "le"),
                        Arrays.asList("v-3", "2.0"),
                        2),
                    new Sample(
                        METRIC_NAME3 + "_bucket",
                        Arrays.asList("k1", "le"),
                        Arrays.asList("v-3", "5.0"),
                        4),
                    new Sample(
                        METRIC_NAME3 + "_bucket",
                        Arrays.asList("k1", "le"),
                        Arrays.asList("v-3", "+Inf"),
                        5),
                    new Sample(
                        METRIC_NAME3 + "_count",
                        Collections.singletonList("k1"),
                        Collections.singletonList("v-3"),
                        5),
                    new Sample(
                        METRIC_NAME3 + "_sum",
                        Collections.singletonList("k1"),
                        Collections.singletonList("v-3"),
                        22.0))));
  }