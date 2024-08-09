@SimpleFunction (description = "The month of the year, a number from 1 to 12)")
  public static int Month(Calendar instant) {
    return Dates.Month(instant) + 1;
  }