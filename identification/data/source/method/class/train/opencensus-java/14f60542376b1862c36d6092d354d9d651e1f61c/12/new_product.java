@VisibleForTesting
  static Metric createMetric(View view, List</*@Nullable*/ TagValue> tagValues, String domain) {
    Metric.Builder builder = Metric.newBuilder();
    // TODO(songya): use pre-defined metrics for canonical views
    builder.setType(generateType(view.getName().asString(), domain));
    Map<String, String> stringTagMap = Maps.newHashMap();
    List<TagKey> columns = view.getColumns();
    checkArgument(
        tagValues.size() == columns.size(), "TagKeys and TagValues don't have same size.");
    for (int i = 0; i < tagValues.size(); i++) {
      TagKey key = columns.get(i);
      TagValue value = tagValues.get(i);
      if (value == null) {
        continue;
      }
      stringTagMap.put(key.getName(), value.asString());
    }
    stringTagMap.put(OPENCENSUS_TASK, OPENCENSUS_TASK_VALUE_DEFAULT);
    builder.putAllLabels(stringTagMap);
    return builder.build();
  }