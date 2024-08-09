public default boolean isLastBusinessDayOfMonth(LocalDate date) {
    ArgChecker.notNull(date, "date");
    return isBusinessDay(date) && next(date).getMonthValue() != date.getMonthValue();
  }