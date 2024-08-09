static AdjustableDate parseAdjustableDate(
      CsvRow row,
      String dateField,
      String conventionField,
      String calendarField,
      BusinessDayConvention defaultConvention,
      Currency currency) {

    LocalDate date = row.getValue(dateField, LoaderUtils::parseDate);
    BusinessDayAdjustment adj = parseBusinessDayAdjustment(row, conventionField, calendarField)
        .orElseGet(() -> BusinessDayAdjustment.of(defaultConvention, HolidayCalendarId.defaultByCurrency(currency)));
    return AdjustableDate.of(date, adj);
  }