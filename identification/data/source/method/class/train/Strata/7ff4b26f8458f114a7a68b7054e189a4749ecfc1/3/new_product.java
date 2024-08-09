public default LocalDate next(LocalDate date) {
    ArgChecker.notNull(date, "date");
    LocalDate next = plusDays(date, 1);
    return isHoliday(next) ? next(next) : next;
  }