public void endCreateCQSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(createCQSendInProgressId, -1);
    int endCreateCQSendId;
    if (failed) {
      endCreateCQSendId = createCQSendFailedId;
    } else {
      endCreateCQSendId = createCQSendId;
    }
    this.sendStats.incInt(endCreateCQSendId, 1);
    this.sendStats.incLong(createCQSendDurationId, duration);
  }