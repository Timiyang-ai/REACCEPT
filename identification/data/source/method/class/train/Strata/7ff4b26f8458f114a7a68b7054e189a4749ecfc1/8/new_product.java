public default LocalDate next(LocalDate date) {
    LocalDate next = plusDays(date, 1);
    return isHoliday(next) ? next(next) : next;
  }