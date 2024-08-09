public default int daysBetween(LocalDateRange dateRange) {
    return Math.toIntExact(dateRange.stream()
        .filter(this::isBusinessDay)
        .count());
  }