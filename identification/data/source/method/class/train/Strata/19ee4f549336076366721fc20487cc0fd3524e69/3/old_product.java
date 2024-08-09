static AdjustableDate parseAdjustableDate(
      CsvRow row,
      String dateField,
      String conventionField,
      String calendarField) {

    LocalDate date = LoaderUtils.parseDate(row.getValue(dateField));
    return parseBusinessDayAdjustment(row, conventionField, calendarField)
        .map(adj -> AdjustableDate.of(date, adj))
        .orElse(AdjustableDate.of(date));
  }