public default LocalDate previousOrSame(LocalDate date) {
    ArgChecker.notNull(date, "date");
    return isHoliday(date) ? previous(date) : date;
  }