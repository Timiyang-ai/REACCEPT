public default LocalDate lastBusinessDayOfMonth(LocalDate date) {
    return previousOrSame(date.withDayOfMonth(date.lengthOfMonth()));
  }