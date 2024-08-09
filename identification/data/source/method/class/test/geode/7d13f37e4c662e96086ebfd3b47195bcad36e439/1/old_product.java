public void endRegisterInstantiatorsSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(registerInstantiatorsSendInProgressId, -1);
    int endRegisterInstantiatorsSendId;
    if (failed) {
      endRegisterInstantiatorsSendId = registerInstantiatorsSendFailedId;
    } else {
      endRegisterInstantiatorsSendId = registerInstantiatorsSendId;
    }
    this.sendStats.incInt(endRegisterInstantiatorsSendId, 1);
    this.stats.incLong(registerInstantiatorsSendDurationId, duration);
  }