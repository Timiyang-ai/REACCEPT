public void endGetDurableCQsSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(getDurableCQsSendsInProgressId, -1);
    int endGetDurableCQsSendId;
    if (failed) {
      endGetDurableCQsSendId = getDurableCQsSendFailedId;
    } else {
      endGetDurableCQsSendId = getDurableCQsSendId;
    }
    this.sendStats.incInt(endGetDurableCQsSendId, 1);
    this.stats.incLong(getDurableCQsSendDurationId, duration);
  }