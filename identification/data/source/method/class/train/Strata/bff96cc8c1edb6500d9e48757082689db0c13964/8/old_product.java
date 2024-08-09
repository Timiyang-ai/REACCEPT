public default LocalDate lastBusinessDayOfMonth(LocalDate date) {
    ArgChecker.notNull(date, "date");
    return previousOrSame(date.withDayOfMonth(date.lengthOfMonth()));
  }