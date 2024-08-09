@Override
  public LocalDate shift(LocalDate date, int amount) {
    ArgChecker.notNull(date, "date");
    try {
      if (amount > 0) {
        // day-of-month: minus one for zero-based day-of-month, plus one to start from next day
        return findNext(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), amount);
      } else if (amount < 0) {
        // day-of-month: minus one for zero-based day-of-month, minus one to start from previous day
        return findPrev(date.getYear(), date.getMonthValue(), date.getDayOfMonth() - 2, amount);
      }
      return date;

    } catch (ArrayIndexOutOfBoundsException ex) {
      if (startYear == 0) {
        return HolidayCalendar.super.shift(date, amount);
      }
      throw new IllegalArgumentException(rangeError(date));
    }
  }