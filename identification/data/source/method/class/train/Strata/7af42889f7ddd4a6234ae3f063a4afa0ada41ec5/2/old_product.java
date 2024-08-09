@Override
  public LocalDate adjust(LocalDate date) {
    ArgChecker.notNull(date, "date");
    LocalDate unadjusted = additionConvention.adjust(date, period, adjustment.getCalendar());
    return adjustment.adjust(unadjusted);
  }