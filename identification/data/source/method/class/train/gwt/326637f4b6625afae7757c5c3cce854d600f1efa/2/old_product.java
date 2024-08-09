public static native long UTC(int year, int month, int date, int hrs,
      int min, int sec) /*-{
    return Date.UTC(year + 1900, month, date, hrs, min, sec);
  }-*/;