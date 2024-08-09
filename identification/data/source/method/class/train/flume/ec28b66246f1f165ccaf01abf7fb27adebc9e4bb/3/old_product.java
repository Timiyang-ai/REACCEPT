public static long roundDownTimeStampMinutes(long timestamp,
      int roundDownMins) throws IllegalStateException {
    Preconditions.checkArgument(roundDownMins > 0 && roundDownMins <= 60,
        "RoundDown must be > 0 and <=60");
    Calendar cal = roundDownField(timestamp, Calendar.MINUTE, roundDownMins);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return cal.getTimeInMillis();

  }