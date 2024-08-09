  @Test
  public void getSamples() {
    assertThat(
            PrometheusExportUtils.getSamples(
                METRIC_NAME, convertToLabelNames(LABEL_KEY), LABEL_VALUE, DOUBLE_VALUE))
        .containsExactly(
            new Sample(METRIC_NAME, Arrays.asList("k1", "k2"), Arrays.asList("v1", "v2"), -5.5));
    assertThat(
            PrometheusExportUtils.getSamples(
                METRIC_NAME,
                convertToLabelNames(Collections.singletonList(K3_LABEL_KEY)),
                Collections.singletonList(V3_LABEL_VALUE),
                LONG_VALUE))
        .containsExactly(
            new Sample(
                METRIC_NAME,
                Collections.singletonList("k_3"),
                Collections.singletonList("v-3"),
                123456789));
    assertThat(
            PrometheusExportUtils.getSamples(
                METRIC_NAME,
                convertToLabelNames(Arrays.asList(K1_LABEL_KEY, K3_LABEL_KEY)),
                Arrays.asList(V1_LABEL_VALUE, null),
                LONG_VALUE))
        .containsExactly(
            new Sample(
                METRIC_NAME, Arrays.asList("k1", "k_3"), Arrays.asList("v1", ""), 123456789));
    assertThat(
            PrometheusExportUtils.getSamples(
                METRIC_NAME,
                convertToLabelNames(Collections.singletonList(K3_LABEL_KEY)),
                Collections.singletonList(V3_LABEL_VALUE),
                SUMMARY_VALUE_2))
        .containsExactly(
            new Sample(
                METRIC_NAME + "_count",
                Collections.singletonList("k_3"),
                Collections.singletonList("v-3"),
                22),
            new Sample(
                METRIC_NAME + "_sum",
                Collections.singletonList("k_3"),
                Collections.singletonList("v-3"),
                74.8),
            new Sample(
                METRIC_NAME,
                Arrays.asList("k_3", LABEL_NAME_QUANTILE),
                Arrays.asList("v-3", "0.995"),
                8.2),
            new Sample(
                METRIC_NAME,
                Arrays.asList("k_3", LABEL_NAME_QUANTILE),
                Arrays.asList("v-3", "0.99"),
                10.2))
        .inOrder();
    assertThat(
            PrometheusExportUtils.getSamples(
                METRIC_NAME,
                convertToLabelNames(Collections.singletonList(K1_LABEL_KEY)),
                Collections.singletonList(V1_LABEL_VALUE),
                DISTRIBUTION_VALUE))
        .containsExactly(
            new Sample(
                METRIC_NAME + "_bucket", Arrays.asList("k1", "le"), Arrays.asList("v1", "1.0"), 0),
            new Sample(
                METRIC_NAME + "_bucket", Arrays.asList("k1", "le"), Arrays.asList("v1", "2.0"), 2),
            new Sample(
                METRIC_NAME + "_bucket", Arrays.asList("k1", "le"), Arrays.asList("v1", "5.0"), 4),
            new Sample(
                METRIC_NAME + "_bucket", Arrays.asList("k1", "le"), Arrays.asList("v1", "+Inf"), 5),
            new Sample(
                METRIC_NAME + "_count",
                Collections.singletonList("k1"),
                Collections.singletonList("v1"),
                5),
            new Sample(
                METRIC_NAME + "_sum",
                Collections.singletonList("k1"),
                Collections.singletonList("v1"),
                22.0))
        .inOrder();
  }