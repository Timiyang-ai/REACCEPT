@Override
  public LocalDate lastBusinessDayOfMonth(LocalDate date) {
    try {
      // find data for month
      int index = (date.getYear() - startYear) * 12 + date.getMonthValue() - 1;
      // need to find the most significant bit, which is the last business day
      // use JDK numberOfLeadingZeros() method which is mapped to a fast intrinsic
      int leading = Integer.numberOfLeadingZeros(lookup[index]);
      return date.withDayOfMonth(32 - leading);

    } catch (ArrayIndexOutOfBoundsException ex) {
      return lastBusinessDayOfMonthOutOfRange(date);
    }
  }