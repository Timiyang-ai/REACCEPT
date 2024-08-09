  @Test
  public void createTimeSeriesList_Cumulative() {
    List<TimeSeries> timeSeriesList =
        StackdriverExportUtils.createTimeSeriesList(
            METRIC,
            DEFAULT_RESOURCE,
            CUSTOM_OPENCENSUS_DOMAIN,
            PROJECT_ID,
            DEFAULT_CONSTANT_LABELS);
    assertThat(timeSeriesList).hasSize(1);
    TimeSeries expectedTimeSeries =
        TimeSeries.newBuilder()
            .setMetricKind(MetricKind.CUMULATIVE)
            .setValueType(MetricDescriptor.ValueType.DOUBLE)
            .setMetric(
                StackdriverExportUtils.createMetric(
                    METRIC_DESCRIPTOR,
                    LABEL_VALUE,
                    CUSTOM_OPENCENSUS_DOMAIN,
                    DEFAULT_CONSTANT_LABELS))
            .setResource(MonitoredResource.newBuilder().setType("global"))
            .addPoints(StackdriverExportUtils.createPoint(POINT, TIMESTAMP_2))
            .build();
    assertThat(timeSeriesList).containsExactly(expectedTimeSeries);
  }