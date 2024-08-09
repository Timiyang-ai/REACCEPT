@Override
  public LocalDate previous(LocalDate date) {
    try {
      // day-of-month: minus one to start from previous day
      return shiftPrev(date.getYear(), date.getMonthValue(), date.getDayOfMonth() - 1, -1);

    } catch (ArrayIndexOutOfBoundsException ex) {
      if (startYear == 0) {
        return HolidayCalendar.super.previous(date);
      }
      throw new IllegalArgumentException(rangeError(date));
    }
  }