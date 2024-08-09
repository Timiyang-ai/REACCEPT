public LocalDate adjust(LocalDate date) {
    LocalDate unadjusted = additionConvention.adjust(date, period, adjustment.getCalendar());
    return adjustment.adjust(unadjusted);
  }