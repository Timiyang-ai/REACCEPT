public void endKeySetSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(keySetSendInProgressId, -1);
    int endKeySetSendId;
    if (failed) {
      endKeySetSendId = keySetSendFailedId;
    } else {
      endKeySetSendId = keySetSendId;
    }
    this.sendStats.incInt(endKeySetSendId, 1);
    this.stats.incLong(keySetSendDurationId, duration);
  }