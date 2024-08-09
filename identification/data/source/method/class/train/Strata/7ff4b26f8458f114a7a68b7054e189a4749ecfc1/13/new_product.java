public default int daysBetween(LocalDate startInclusive, LocalDate endExclusive) {
    return Math.toIntExact(LocalDateUtils.stream(startInclusive, endExclusive)
        .filter(this::isBusinessDay)
        .count());
  }