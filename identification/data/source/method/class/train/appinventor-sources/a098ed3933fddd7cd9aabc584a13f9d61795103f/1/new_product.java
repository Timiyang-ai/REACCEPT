@SimpleFunction
  public static String FormatDate(Calendar date, String pattern) {
    SimpleDateFormat formatdate = new SimpleDateFormat();
    if (pattern.length() == 0) {
      formatdate.applyPattern("MMM d, yyyy");
    } else {
      formatdate.applyPattern(pattern);
    }
      return formatdate.format(date.getTime());
  }