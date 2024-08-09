static List<TimeSeries> createTimeSeriesList(
      @javax.annotation.Nullable ViewData viewData, MonitoredResource monitoredResource) {
    List<TimeSeries> timeSeriesList = Lists.newArrayList();
    if (viewData == null) {
      return timeSeriesList;
    }
    View view = viewData.getView();
    if (!(view.getWindow() instanceof Cumulative)) {
      // TODO(songya): Only Cumulative view will be exported to Stackdriver in this version.
      return timeSeriesList;
    }

    // Shared fields for all TimeSeries generated from the same ViewData
    TimeSeries.Builder shared = TimeSeries.newBuilder();
    shared.setMetricKind(createMetricKind(view.getWindow()));
    shared.setResource(monitoredResource);
    shared.setValueType(createValueType(view.getAggregation(), view.getMeasure()));

    // Each entry in AggregationMap will be converted into an independent TimeSeries object
    for (Entry<List<TagValue>, AggregationData> entry : viewData.getAggregationMap().entrySet()) {
      TimeSeries.Builder builder = shared.clone();
      builder.setMetric(createMetric(view, entry.getKey()));
      builder.addPoints(
          createPoint(entry.getValue(), viewData.getWindowData(), view.getAggregation()));
      timeSeriesList.add(builder.build());
    }

    return timeSeriesList;
  }