public void endGetSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(getSendInProgressId, -1);
    int endGetSendId;
    if (failed) {
      endGetSendId = getSendFailedId;
    } else {
      endGetSendId = getSendId;
    }
    this.sendStats.incInt(endGetSendId, 1);
    this.sendStats.incLong(getSendDurationId, duration);
  }