public void endGet(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(getInProgressId, -1);
    int endGetId;
    if (timedOut) {
      endGetId = getTimedOutId;
    } else if (failed) {
      endGetId = getFailedId;
    } else {
      endGetId = getId;
    }
    this.stats.incInt(endGetId, 1);
    this.stats.incLong(getDurationId, duration);
  }