public static long roundDownTimeStampSeconds(long timestamp,
      int roundDownSec) throws IllegalStateException {
    Preconditions.checkArgument(roundDownSec > 0 && roundDownSec <= 60,
        "RoundDownSec must be > 0 and <=60");
    Calendar cal = roundDownField(timestamp, Calendar.SECOND, roundDownSec);
    cal.set(Calendar.MILLISECOND, 0);
    return cal.getTimeInMillis();
  }