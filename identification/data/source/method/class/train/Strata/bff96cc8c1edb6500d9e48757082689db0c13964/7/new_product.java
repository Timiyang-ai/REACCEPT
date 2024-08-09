@Override
  public LocalDate nextSameOrLastInMonth(LocalDate date) {
    try {
      // day-of-month: no alteration as method is one-based and same is valid
      return shiftNextSameLast(date);

    } catch (ArrayIndexOutOfBoundsException ex) {
      return HolidayCalendar.super.nextSameOrLastInMonth(date);
    }
  }