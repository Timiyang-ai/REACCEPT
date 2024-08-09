public void endDestroyRegionSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(destroyRegionSendInProgressId, -1);
    int endDestroyRegionSendId;
    if (failed) {
      endDestroyRegionSendId = destroyRegionSendFailedId;
    } else {
      endDestroyRegionSendId = destroyRegionSendId;
    }
    this.sendStats.incInt(endDestroyRegionSendId, 1);
    this.stats.incLong(destroyRegionSendDurationId, duration);
  }