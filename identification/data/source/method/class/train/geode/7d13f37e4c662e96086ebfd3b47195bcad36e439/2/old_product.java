public void endRegisterInterestSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(registerInterestSendInProgressId, -1);
    int endRegisterInterestSendId;
    if (failed) {
      endRegisterInterestSendId = registerInterestSendFailedId;
    } else {
      endRegisterInterestSendId = registerInterestSendId;
    }
    this.sendStats.incInt(endRegisterInterestSendId, 1);
    this.stats.incLong(registerInterestSendDurationId, duration);
  }