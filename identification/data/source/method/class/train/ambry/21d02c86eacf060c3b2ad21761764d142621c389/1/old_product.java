public static long addSecondsToEpochTime(long epochTimeInMs, long deltaTimeInSeconds) {
    if (deltaTimeInSeconds == Infinite_Time || epochTimeInMs == Infinite_Time) {
      return Infinite_Time;
    }
    return epochTimeInMs + (deltaTimeInSeconds * Time.MsPerSec);
  }