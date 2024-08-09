@Override
  public LocalDate shift(LocalDate date, int amount) {
    try {
      if (amount > 0) {
        // day-of-month: minus one for zero-based day-of-month, plus one to start from next day
        return shiftNext(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), amount);
      } else if (amount < 0) {
        // day-of-month: minus one to start from previous day
        return shiftPrev(date.getYear(), date.getMonthValue(), date.getDayOfMonth() - 1, amount);
      }
      return date;

    } catch (ArrayIndexOutOfBoundsException ex) {
      if (startYear == 0) {
        return HolidayCalendar.super.shift(date, amount);
      }
      throw new IllegalArgumentException(rangeError(date));
    }
  }