@Deprecated
  public List<Double> resolveValues(List<SchedulePeriod> periods) {
    return resolveValues(periods, RollConventions.NONE).toList();
  }