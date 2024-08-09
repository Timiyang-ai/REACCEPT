public void endContainsKeySend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(containsKeySendInProgressId, -1);
    int endContainsKeySendId;
    if (failed) {
      endContainsKeySendId = containsKeySendFailedId;
    } else {
      endContainsKeySendId = containsKeySendId;
    }
    this.sendStats.incInt(endContainsKeySendId, 1);
    this.stats.incLong(containsKeySendDurationId, duration);
  }