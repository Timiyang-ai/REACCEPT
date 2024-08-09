public static long UTC(int year, int month, int date, int hrs, int min,
      int sec) {
    return (long) NativeDate.UTC(year + 1900, month, date, hrs, min, sec, 0);
  }