public void endPut(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(putInProgressId, -1);
    int endPutId;
    if (timedOut) {
      endPutId = putTimedOutId;
    } else if (failed) {
      endPutId = putFailedId;
    } else {
      endPutId = putId;
    }
    this.stats.incInt(endPutId, 1);
    this.stats.incLong(putDurationId, duration);
  }