@Override
  public long getBlacklistDurationMsFor(
      int dataType, long loadDurationMs, IOException exception, int errorCount) {
    if (exception instanceof InvalidResponseCodeException) {
      int responseCode = ((InvalidResponseCodeException) exception).responseCode;
      return responseCode == 404 // HTTP 404 Not Found.
              || responseCode == 410 // HTTP 410 Gone.
          ? DEFAULT_TRACK_BLACKLIST_MS
          : C.TIME_UNSET;
    }
    return C.TIME_UNSET;
  }