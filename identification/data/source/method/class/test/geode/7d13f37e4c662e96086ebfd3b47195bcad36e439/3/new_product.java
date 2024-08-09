public void endPrimaryAckSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(primaryAckSendInProgressId, -1);
    int endPrimaryAckSendId;
    if (failed) {
      endPrimaryAckSendId = primaryAckSendFailedId;
    } else {
      endPrimaryAckSendId = primaryAckSendId;
    }
    this.sendStats.incInt(endPrimaryAckSendId, 1);
    this.sendStats.incLong(primaryAckSendDurationId, duration);
  }