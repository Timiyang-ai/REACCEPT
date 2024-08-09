public LocalDate adjust(LocalDate date) {
    LocalDate unadjusted = additionConvention.adjust(date, tenor.getPeriod(), adjustment.getCalendar());
    return adjustment.adjust(unadjusted);
  }