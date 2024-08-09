@Override
  public LocalDate next(LocalDate date) {
    ArgChecker.notNull(date, "date");
    try {
      // day-of-month: minus one for zero-based day-of-month, plus one to start from next day
      return findNext(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), 1);

    } catch (ArrayIndexOutOfBoundsException ex) {
      if (startYear == 0) {
        return HolidayCalendar.super.next(date);
      }
      throw new IllegalArgumentException(rangeError(date));
    }
  }