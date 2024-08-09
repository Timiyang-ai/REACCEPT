  @Test
  public void create_WithNullMetricDescriptor() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("metricDescriptor");
    Metric.create(null, Collections.<TimeSeries>emptyList());
  }