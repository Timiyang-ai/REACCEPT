public void endClearSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(clearSendInProgressId, -1);
    int endClearSendId;
    if (failed) {
      endClearSendId = clearSendFailedId;
    } else {
      endClearSendId = clearSendId;
    }
    this.sendStats.incInt(endClearSendId, 1);
    this.stats.incLong(clearSendDurationId, duration);
  }