public default LocalDate previousOrSame(LocalDate date) {
    return isHoliday(date) ? previous(date) : date;
  }