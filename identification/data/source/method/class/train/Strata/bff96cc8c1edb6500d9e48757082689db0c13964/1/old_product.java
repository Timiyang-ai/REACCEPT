@Override
  public LocalDate nextSameOrLastInMonth(LocalDate date) {
    ArgChecker.notNull(date, "date");
    try {
      // day-of-month: no alteration as method is one-based and same is valid
      return shiftNextSameLast(date.getYear(), date.getMonthValue(), date.getDayOfMonth());

    } catch (ArrayIndexOutOfBoundsException ex) {
      if (startYear == 0) {
        return HolidayCalendar.super.nextSameOrLastInMonth(date);
      }
      throw new IllegalArgumentException(rangeError(date));
    }
  }