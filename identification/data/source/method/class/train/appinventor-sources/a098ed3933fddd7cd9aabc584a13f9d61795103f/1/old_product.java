@SimpleFunction
  public static String FormatDate(Calendar date) {
    return DateFormat.getDateInstance(DateFormat.MEDIUM).format(date.getTime());
  }