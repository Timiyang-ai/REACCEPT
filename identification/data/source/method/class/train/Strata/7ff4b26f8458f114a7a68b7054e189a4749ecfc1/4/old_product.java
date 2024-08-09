public int daysBetween(LocalDateRange dateRange) {
    ArgChecker.notNull(dateRange, "dateRange");
    if (hasNoHolidays()) {
      return Math.toIntExact(DAYS.between(dateRange.getStart(), dateRange.getEndExclusive()));
    }
    return Math.toIntExact(dateRange.stream()
        .filter(this::isBusinessDay)
        .count());
  }