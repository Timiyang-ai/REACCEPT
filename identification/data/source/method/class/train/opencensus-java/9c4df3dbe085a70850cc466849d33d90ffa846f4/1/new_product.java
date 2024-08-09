public static TimeSeries create(
      List<LabelValue> labelValues, List<Point> points, @Nullable Timestamp startTimestamp) {
    Utils.checkListElementNotNull(Utils.checkNotNull(points, "points"), "point");
    return createInternal(
        labelValues, Collections.unmodifiableList(new ArrayList<Point>(points)), startTimestamp);
  }