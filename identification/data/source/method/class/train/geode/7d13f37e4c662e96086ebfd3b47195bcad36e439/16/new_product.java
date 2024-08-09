public void endGatewayBatchSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(gatewayBatchSendInProgressId, -1);
    int endGatewayBatchSendId;
    if (failed) {
      endGatewayBatchSendId = gatewayBatchSendFailedId;
    } else {
      endGatewayBatchSendId = gatewayBatchSendId;
    }
    this.sendStats.incInt(endGatewayBatchSendId, 1);
    this.sendStats.incLong(gatewayBatchSendDurationId, duration);
  }