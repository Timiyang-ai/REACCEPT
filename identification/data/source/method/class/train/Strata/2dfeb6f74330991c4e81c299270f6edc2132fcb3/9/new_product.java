@Override
  public LocalDate next(LocalDate date) {
    try {
      // day-of-month: minus one for zero-based day-of-month, plus one to start from next day
      return shiftNext(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), 1);

    } catch (ArrayIndexOutOfBoundsException ex) {
      return HolidayCalendar.super.next(date);
    }
  }