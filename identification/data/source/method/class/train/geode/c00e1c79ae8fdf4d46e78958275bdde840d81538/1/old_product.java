public void endDestroy(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(destroyInProgressId, -1);
    int endDestroyId;
    if (timedOut) {
      endDestroyId = destroyTimedOutId;
    } else if (failed) {
      endDestroyId = destroyFailedId;
    } else {
      endDestroyId = destroyId;
    }
    this.stats.incInt(endDestroyId, 1);
    this.stats.incLong(destroyDurationId, duration);
  }