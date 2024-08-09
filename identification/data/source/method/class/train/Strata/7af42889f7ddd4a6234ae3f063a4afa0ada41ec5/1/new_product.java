public LocalDate adjust(LocalDate date, ReferenceData refData) {
    HolidayCalendar holCal = adjustment.getCalendar();
    BusinessDayConvention bda = adjustment.getConvention();
    return bda.adjust(additionConvention.adjust(date, period, holCal), holCal);
  }