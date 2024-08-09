public void endGetAllSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(getAllSendInProgressId, -1);
    int endGetAllSendId;
    if (failed) {
      endGetAllSendId = getAllSendFailedId;
    } else {
      endGetAllSendId = getAllSendId;
    }
    this.sendStats.incInt(endGetAllSendId, 1);
    this.sendStats.incLong(getAllSendDurationId, duration);
  }