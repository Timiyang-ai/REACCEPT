public Schedule toUnadjusted() {
    return toBuilder()
        .periods(periods.stream()
            .map(p -> SchedulePeriod.of(p.getUnadjustedStartDate(), p.getUnadjustedEndDate()))
            .collect(toImmutableList()))
        .build();
  }