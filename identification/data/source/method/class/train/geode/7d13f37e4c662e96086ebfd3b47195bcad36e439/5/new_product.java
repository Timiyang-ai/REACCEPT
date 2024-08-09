public void endGetClientPartitionAttributesSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(getClientPartitionAttributesSendInProgressId, -1);
    int endGetClientPartitionAttributesSendId;
    if (failed) {
      endGetClientPartitionAttributesSendId = getClientPartitionAttributesSendFailedId;
    } else {
      endGetClientPartitionAttributesSendId = getClientPartitionAttributesSendId;
    }
    this.sendStats.incInt(endGetClientPartitionAttributesSendId, 1);
    this.sendStats.incLong(getClientPartitionAttributesSendDurationId, duration);
  }