  @Test
  public void createMetricDescriptor() {
    MetricDescriptor metricDescriptor =
        StackdriverExportUtils.createMetricDescriptor(
            METRIC_DESCRIPTOR,
            PROJECT_ID,
            "custom.googleapis.com/myorg/",
            "myorg/",
            DEFAULT_CONSTANT_LABELS);
    assertThat(metricDescriptor.getName())
        .isEqualTo(
            "projects/"
                + PROJECT_ID
                + "/metricDescriptors/custom.googleapis.com/myorg/"
                + METRIC_NAME);
    assertThat(metricDescriptor.getDescription()).isEqualTo(METRIC_DESCRIPTION);
    assertThat(metricDescriptor.getDisplayName()).isEqualTo("myorg/" + METRIC_NAME);
    assertThat(metricDescriptor.getType()).isEqualTo("custom.googleapis.com/myorg/" + METRIC_NAME);
    assertThat(metricDescriptor.getUnit()).isEqualTo(METRIC_UNIT);
    assertThat(metricDescriptor.getMetricKind()).isEqualTo(MetricKind.CUMULATIVE);

    assertThat(metricDescriptor.getValueType()).isEqualTo(MetricDescriptor.ValueType.DOUBLE);
    assertThat(metricDescriptor.getLabelsList())
        .containsExactly(
            LabelDescriptor.newBuilder()
                .setKey(LABEL_KEY.get(0).getKey())
                .setDescription(LABEL_KEY.get(0).getDescription())
                .setValueType(ValueType.STRING)
                .build(),
            LabelDescriptor.newBuilder()
                .setKey(StackdriverExportUtils.OPENCENSUS_TASK_KEY.getKey())
                .setDescription(StackdriverExportUtils.OPENCENSUS_TASK_KEY.getDescription())
                .setValueType(ValueType.STRING)
                .build());
  }