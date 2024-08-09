public default boolean isLastBusinessDayOfMonth(LocalDate date) {
    return isBusinessDay(date) && next(date).getMonthValue() != date.getMonthValue();
  }