public void endCloseConSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(closeConSendInProgressId, -1);
    int endCloseConSendId;
    if (failed) {
      endCloseConSendId = closeConSendFailedId;
    } else {
      endCloseConSendId = closeConSendId;
    }
    this.sendStats.incInt(endCloseConSendId, 1);
    this.stats.incLong(closeConSendDurationId, duration);
  }