  @Test
  public void createMetric() {
    assertThat(
            StackdriverExportUtils.createMetric(
                METRIC_DESCRIPTOR, LABEL_VALUE, CUSTOM_OPENCENSUS_DOMAIN, DEFAULT_CONSTANT_LABELS))
        .isEqualTo(
            Metric.newBuilder()
                .setType("custom.googleapis.com/opencensus/" + METRIC_NAME)
                .putLabels("KEY1", "VALUE1")
                .putLabels(StackdriverExportUtils.OPENCENSUS_TASK_KEY.getKey(), DEFAULT_TASK_VALUE)
                .build());
  }