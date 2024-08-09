@SimpleFunction
  public static Calendar DateValue(String value) {
    Calendar date = new GregorianCalendar();
    date.setTime(tryParseDate(value));
    return date;
  }