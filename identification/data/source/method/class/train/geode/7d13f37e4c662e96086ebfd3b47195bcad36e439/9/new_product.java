public void endGetClientPRMetadataSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(getClientPRMetadataSendInProgressId, -1);
    int endGetClientPRMetadataSendId;
    if (failed) {
      endGetClientPRMetadataSendId = getClientPRMetadataSendFailedId;
    } else {
      endGetClientPRMetadataSendId = getClientPRMetadataSendId;
    }
    this.sendStats.incInt(endGetClientPRMetadataSendId, 1);
    this.sendStats.incLong(getClientPRMetadataSendDurationId, duration);
  }