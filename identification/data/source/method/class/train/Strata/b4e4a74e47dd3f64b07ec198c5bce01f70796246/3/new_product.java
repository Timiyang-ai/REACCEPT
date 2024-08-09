public LocalDate adjust(LocalDate date, ReferenceData refData) {
    HolidayCalendar holCal = adjustment.getCalendar().resolve(refData);
    BusinessDayConvention bda = adjustment.getConvention();
    return bda.adjust(additionConvention.adjust(date, tenor.getPeriod(), holCal), holCal);
  }