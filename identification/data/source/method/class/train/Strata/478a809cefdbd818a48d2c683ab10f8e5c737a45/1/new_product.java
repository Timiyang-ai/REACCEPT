public Schedule toUnadjusted() {
    return toBuilder()
        .periods(periods.stream()
            .map(p -> p.toUnadjusted())
            .collect(toImmutableList()))
        .build();
  }