@SimpleFunction
  public static Calendar DateValue(String value) {
    Calendar date = new GregorianCalendar();
    try {
      DateFormat dateTimeFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
      dateTimeFormat.setLenient(true);
      date.setTime(dateTimeFormat.parse(value));
    } catch (ParseException e) {
      try {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(true);
        date.setTime(dateFormat.parse(value));
      } catch (ParseException e1) {
        try {
          DateFormat dateFormat = new SimpleDateFormat("HH:mm");
          dateFormat.setLenient(true);
          date.setTime(dateFormat.parse(value));
        } catch (ParseException pe) {
          throw new IllegalArgumentException("illegal date/time format in function DateValue()");
        }
      }
    }
    return date;
  }