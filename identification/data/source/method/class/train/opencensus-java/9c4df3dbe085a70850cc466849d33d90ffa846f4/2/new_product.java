public static TimeSeries create(
      List<LabelValue> labelValues, List<Point> points, @Nullable Timestamp startTimestamp) {
    Utils.checkNotNull(points, "points");
    Utils.checkListElementNotNull(points, "point");
    return createInternal(
        labelValues, Collections.unmodifiableList(new ArrayList<Point>(points)), startTimestamp);
  }