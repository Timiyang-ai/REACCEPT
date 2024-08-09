public default LocalDate nextOrSame(LocalDate date) {
    return isHoliday(date) ? next(date) : date;
  }