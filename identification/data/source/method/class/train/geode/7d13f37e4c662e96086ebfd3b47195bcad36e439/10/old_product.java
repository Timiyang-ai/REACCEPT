public void endDestroySend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(destroySendInProgressId, -1);
    int endDestroySendId;
    if (failed) {
      endDestroySendId = destroySendFailedId;
    } else {
      endDestroySendId = destroySendId;
    }
    this.sendStats.incInt(endDestroySendId, 1);
    this.stats.incLong(destroySendDurationId, duration);
  }