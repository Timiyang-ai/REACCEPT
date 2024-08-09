public void endExecuteFunctionSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    this.sendStats.incInt(executeFunctionSendInProgressId, -1);
    int endExecuteFunctionSendId;
    if (failed) {
      endExecuteFunctionSendId = executeFunctionSendFailedId;
    } else {
      endExecuteFunctionSendId = executeFunctionSendId;
    }
    this.sendStats.incInt(endExecuteFunctionSendId, 1);
    this.sendStats.incLong(executeFunctionSendDurationId, duration);
  }