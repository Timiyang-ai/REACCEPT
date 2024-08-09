static AdjustableDate parseAdjustableDate(
      CsvRow row,
      String dateField,
      String conventionField,
      String calendarField,
      BusinessDayConvention defaultConvention,
      Currency currency) {

    LocalDate date = LoaderUtils.parseDate(row.getValue(dateField));
    BusinessDayAdjustment adj = parseBusinessDayAdjustment(row, conventionField, calendarField)
        .orElseGet(() -> BusinessDayAdjustment.of(defaultConvention, HolidayCalendarId.defaultByCurrency(currency)));
    return AdjustableDate.of(date, adj);
  }