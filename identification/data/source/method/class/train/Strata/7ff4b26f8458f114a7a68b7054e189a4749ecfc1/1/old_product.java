public int daysBetween(LocalDateRange dateRange) {
    ArgChecker.notNull(dateRange, "dateRange");
    return Math.toIntExact(dateRange.stream()
        .filter(this::isBusinessDay)
        .count());
  }