public void endRemoveAllSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(removeAllSendInProgressId, -1);
    int endRemoveAllSendId;
    if (failed) {
      endRemoveAllSendId = removeAllSendFailedId;
    } else {
      endRemoveAllSendId = removeAllSendId;
    }
    this.sendStats.incInt(endRemoveAllSendId, 1);
    this.stats.incLong(removeAllSendDurationId, duration);
  }