@Override
  public boolean isLastBusinessDayOfMonth(LocalDate date) {
    try {
      // find data for month
      int index = (date.getYear() - startYear) * 12 + date.getMonthValue() - 1;
      // shift right, leaving the input date as bit-0 and filling with 0 on the left
      // if the result is 1, which is all zeroes and a final 1 (...0001) then it is last business day of month
      return (lookup[index] >>> (date.getDayOfMonth() - 1)) == 1;

    } catch (ArrayIndexOutOfBoundsException ex) {
      return isLastBusinessDayOfMonthOutOfRange(date);
    }
  }