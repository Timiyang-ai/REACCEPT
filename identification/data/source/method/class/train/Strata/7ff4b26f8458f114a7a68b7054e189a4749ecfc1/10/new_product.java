public default LocalDate previous(LocalDate date) {
    LocalDate previous = plusDays(date, -1);
    return isHoliday(previous) ? previous(previous) : previous;
  }