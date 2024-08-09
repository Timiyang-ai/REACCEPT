@Override
  public LocalDate adjust(LocalDate date) {
    ArgChecker.notNull(date, "date");
    LocalDate added = date.plus(period);
    return adjustment.adjust(added);
  }