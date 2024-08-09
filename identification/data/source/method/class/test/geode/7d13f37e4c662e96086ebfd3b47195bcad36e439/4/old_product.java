public void endStopCQSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(stopCQSendInProgressId, -1);
    int endStopCQSendId;
    if (failed) {
      endStopCQSendId = stopCQSendFailedId;
    } else {
      endStopCQSendId = stopCQSendId;
    }
    this.sendStats.incInt(endStopCQSendId, 1);
    this.stats.incLong(stopCQSendDurationId, duration);
  }