  @Test
  public void endStopCQSend_FailedOperation() {
    int statId = ConnectionStats.getSendType().nameToId("stopCQSendFailures");

    connectionStats.endStopCQSend(1, true);

    verify(sendStats).incInt(eq(stopCQSendInProgressId), eq(-1));
    verify(sendStats).incInt(statId, 1);
    verify(sendStats).incLong(eq(stopCQSendDurationId), anyLong());
  }