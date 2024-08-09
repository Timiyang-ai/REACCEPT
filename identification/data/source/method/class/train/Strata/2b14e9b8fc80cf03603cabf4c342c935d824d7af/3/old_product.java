public Schedule toAdjusted(BusinessDayAdjustment businessDayAdjustment) {
    // implementation needs to return 'this' if unchanged to optimize downstream code
    boolean adjusted = false;
    ImmutableList.Builder<SchedulePeriod> builder = ImmutableList.builder();
    for (SchedulePeriod period : periods) {
      SchedulePeriod adjPeriod = period.toAdjusted(businessDayAdjustment);
      builder.add(adjPeriod);
      adjusted |= (adjPeriod != period);
    }
    return adjusted ? new Schedule(builder.build(), frequency, rollConvention) : this;
  }