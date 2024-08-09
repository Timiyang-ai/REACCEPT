public void endCloseCQSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(closeCQSendInProgressId, -1);
    int endCloseCQSendId;
    if (failed) {
      endCloseCQSendId = closeCQSendFailedId;
    } else {
      endCloseCQSendId = closeCQSendId;
    }
    this.sendStats.incInt(endCloseCQSendId, 1);
    this.sendStats.incLong(closeCQSendDurationId, duration);
  }