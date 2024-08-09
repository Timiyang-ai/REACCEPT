public void endClear(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(clearInProgressId, -1);
    int endClearId;
    if (timedOut) {
      endClearId = clearTimedOutId;
    } else if (failed) {
      endClearId = clearFailedId;
    } else {
      endClearId = clearId;
    }
    this.stats.incInt(endClearId, 1);
    this.stats.incLong(clearDurationId, duration);
  }