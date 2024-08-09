@Override
  public LocalDate adjust(LocalDate date) {
    ArgChecker.notNull(date, "date");
    LocalDate unadjusted = additionConvention.adjust(date, tenor.getPeriod(), adjustment.getCalendar());
    return adjustment.adjust(unadjusted);
  }