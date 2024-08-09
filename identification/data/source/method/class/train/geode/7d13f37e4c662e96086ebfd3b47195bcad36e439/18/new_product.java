public void endPutSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(putSendInProgressId, -1);
    int endPutSendId;
    if (failed) {
      endPutSendId = putSendFailedId;
    } else {
      endPutSendId = putSendId;
    }
    this.sendStats.incInt(endPutSendId, 1);
    this.sendStats.incLong(putSendDurationId, duration);
  }