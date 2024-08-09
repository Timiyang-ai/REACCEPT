@SimpleFunction (description = "The day of the week. a number from 1 (Sunday) to 7 (Saturday)")
  public static int Weekday(Calendar instant) {
    return Dates.Weekday(instant);
  }