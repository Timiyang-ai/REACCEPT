public LocalDate next(LocalDate date) {
    ArgChecker.notNull(date, "date");
    LocalDate next = date.plusDays(1);
    return isHoliday(next) ? next(next) : next;
  }