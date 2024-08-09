@Override
  public boolean isLastBusinessDayOfMonth(LocalDate date) {
    ArgChecker.notNull(date, "date");
    try {
      // find data for month
      int index = (date.getYear() - startYear) * 12 + date.getMonthValue() - 1;
      // shift right, leaving the input date as bit-0 and filling with 1 on the right
      // if the result is -2, which is all ones and a final 0 (...1110) then it is last business day of month
      return (lookup[index] >> (date.getDayOfMonth() - 1)) == -2;

    } catch (ArrayIndexOutOfBoundsException ex) {
      if (startYear == 0) {
        return HolidayCalendar.super.isLastBusinessDayOfMonth(date);
      }
      throw new IllegalArgumentException("Date is not within the range of known holidays: " + date + ", " + range);
    }
  }