public void endUnregisterInterestSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(unregisterInterestSendInProgressId, -1);
    int endUnregisterInterestSendId;
    if (failed) {
      endUnregisterInterestSendId = unregisterInterestSendFailedId;
    } else {
      endUnregisterInterestSendId = unregisterInterestSendId;
    }
    this.sendStats.incInt(endUnregisterInterestSendId, 1);
    this.sendStats.incLong(unregisterInterestSendDurationId, duration);
  }