public default LocalDate nextOrSame(LocalDate date) {
    ArgChecker.notNull(date, "date");
    return isHoliday(date) ? next(date) : date;
  }