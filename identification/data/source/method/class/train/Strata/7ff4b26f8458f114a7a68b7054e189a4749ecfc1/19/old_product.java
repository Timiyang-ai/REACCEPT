public int daysBetween(LocalDate startInclusive, LocalDate endExclusive) {
    ArgChecker.notNull(startInclusive, "startInclusive");
    ArgChecker.notNull(endExclusive, "endExclusive");
    if (startInclusive.equals(endExclusive)) {
      return 0;
    }
    return daysBetween(LocalDateRange.halfOpen(startInclusive, endExclusive));
  }