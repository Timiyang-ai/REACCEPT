public default int daysBetween(LocalDate startInclusive, LocalDate endExclusive) {
    ArgChecker.inOrderOrEqual(startInclusive, endExclusive, "startInclusive", "endExclusive");
    return Math.toIntExact(LocalDateUtils.stream(startInclusive, endExclusive)
        .filter(this::isBusinessDay)
        .count());
  }