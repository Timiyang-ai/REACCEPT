public void endPutAllSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(putAllSendInProgressId, -1);
    int endPutAllSendId;
    if (failed) {
      endPutAllSendId = putAllSendFailedId;
    } else {
      endPutAllSendId = putAllSendId;
    }
    this.sendStats.incInt(endPutAllSendId, 1);
    this.stats.incLong(putAllSendDurationId, duration);
  }