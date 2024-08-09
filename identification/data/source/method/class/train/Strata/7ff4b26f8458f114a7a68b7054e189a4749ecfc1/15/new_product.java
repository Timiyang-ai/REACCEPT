public default int daysBetween(LocalDate startInclusive, LocalDate endExclusive) {
    return daysBetween(LocalDateRange.of(startInclusive, endExclusive));
  }