public static TimeSeries create(
      List<LabelValue> labelValues, List<Point> points, @Nullable Timestamp startTimestamp) {
    // Fail fast on null lists to prevent NullPointerException when copying the lists.
    Utils.checkNotNull(labelValues, "labelValues");
    Utils.checkNotNull(points, "points");
    Utils.checkListElementNotNull(labelValues, "labelValue");
    Utils.checkListElementNotNull(points, "point");
    return new AutoValue_TimeSeries(
        Collections.unmodifiableList(new ArrayList<LabelValue>(labelValues)),
        Collections.unmodifiableList(new ArrayList<Point>(points)),
        startTimestamp);
  }