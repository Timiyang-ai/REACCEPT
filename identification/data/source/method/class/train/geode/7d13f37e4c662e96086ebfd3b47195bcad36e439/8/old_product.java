public void endPingSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(pingSendInProgressId, -1);
    int endPingSendId;
    if (failed) {
      endPingSendId = pingSendFailedId;
    } else {
      endPingSendId = pingSendId;
    }
    this.sendStats.incInt(endPingSendId, 1);
    this.stats.incLong(pingSendDurationId, duration);
  }