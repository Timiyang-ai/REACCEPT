  @Test
  public void endGatewayBatchSend_FailedOperation() {
    int statId = ConnectionStats.getSendType().nameToId("gatewayBatchSendFailures");

    connectionStats.endGatewayBatchSend(1, true);

    verify(sendStats).incInt(eq(gatewayBatchSendInProgressId), eq(-1));
    verify(sendStats).incInt(statId, 1);
    verify(sendStats).incLong(eq(gatewayBatchSendDurationId), anyLong());
  }