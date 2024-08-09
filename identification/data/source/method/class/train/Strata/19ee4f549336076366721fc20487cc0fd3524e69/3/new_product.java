static AdjustableDate parseAdjustableDate(
      CsvRow row,
      String dateField,
      String conventionField,
      String calendarField) {

    LocalDate date = row.getValue(dateField, LoaderUtils::parseDate);
    return parseBusinessDayAdjustment(row, conventionField, calendarField)
        .map(adj -> AdjustableDate.of(date, adj))
        .orElse(AdjustableDate.of(date));
  }