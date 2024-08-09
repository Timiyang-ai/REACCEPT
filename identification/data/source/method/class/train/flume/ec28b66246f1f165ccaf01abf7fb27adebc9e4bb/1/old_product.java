public static long roundDownTimeStampHours(long timestamp,
      int roundDownHours) throws IllegalStateException {
    Preconditions.checkArgument(roundDownHours > 0 && roundDownHours <= 24,
        "RoundDown must be > 0 and <=24");
    Calendar cal = roundDownField(timestamp,
        Calendar.HOUR_OF_DAY, roundDownHours);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return cal.getTimeInMillis();
  }