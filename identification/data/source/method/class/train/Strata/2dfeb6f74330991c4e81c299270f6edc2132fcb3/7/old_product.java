@Override
  public LocalDate previous(LocalDate date) {
    ArgChecker.notNull(date, "date");
    try {
      // day-of-month: minus one for zero-based day-of-month, minus one to start from previous day
      return findPrev(date.getYear(), date.getMonthValue(), date.getDayOfMonth() - 2, -1);

    } catch (ArrayIndexOutOfBoundsException ex) {
      if (startYear == 0) {
        return HolidayCalendar.super.previous(date);
      }
      throw new IllegalArgumentException(rangeError(date));
    }
  }