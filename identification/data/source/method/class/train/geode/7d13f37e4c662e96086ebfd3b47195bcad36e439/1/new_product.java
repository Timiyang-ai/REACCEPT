public void endMakePrimarySend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(makePrimarySendInProgressId, -1);
    int endMakePrimarySendId;
    if (failed) {
      endMakePrimarySendId = makePrimarySendFailedId;
    } else {
      endMakePrimarySendId = makePrimarySendId;
    }
    this.sendStats.incInt(endMakePrimarySendId, 1);
    this.sendStats.incLong(makePrimarySendDurationId, duration);
  }