public Schedule toAdjusted(DateAdjuster adjuster) {
    // implementation needs to return 'this' if unchanged to optimize downstream code
    boolean adjusted = false;
    ImmutableList.Builder<SchedulePeriod> builder = ImmutableList.builder();
    for (SchedulePeriod period : periods) {
      SchedulePeriod adjPeriod = period.toAdjusted(adjuster);
      builder.add(adjPeriod);
      adjusted |= (adjPeriod != period);
    }
    return adjusted ? new Schedule(builder.build(), frequency, rollConvention) : this;
  }