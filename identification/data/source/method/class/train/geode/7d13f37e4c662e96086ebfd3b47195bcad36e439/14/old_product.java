public void endReadyForEventsSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(readyForEventsSendInProgressId, -1);
    int endReadyForEventsSendId;
    if (failed) {
      endReadyForEventsSendId = readyForEventsSendFailedId;
    } else {
      endReadyForEventsSendId = readyForEventsSendId;
    }
    this.sendStats.incInt(endReadyForEventsSendId, 1);
    this.stats.incLong(readyForEventsSendDurationId, duration);
  }