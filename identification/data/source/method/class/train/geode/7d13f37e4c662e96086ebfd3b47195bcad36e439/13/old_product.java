public void endQuerySend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(querySendInProgressId, -1);
    int endQuerySendId;
    if (failed) {
      endQuerySendId = querySendFailedId;
    } else {
      endQuerySendId = querySendId;
    }
    this.sendStats.incInt(endQuerySendId, 1);
    this.stats.incLong(querySendDurationId, duration);
  }