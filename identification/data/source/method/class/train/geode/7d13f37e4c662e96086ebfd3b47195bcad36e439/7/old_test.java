  @Test
  public void endCloseCQSend_FailedOperation() {
    int statId = ConnectionStats.getSendType().nameToId("closeCQSendFailures");

    connectionStats.endCloseCQSend(1, true);

    verify(sendStats).incInt(eq(closeCQSendInProgressId), eq(-1));
    verify(sendStats).incInt(statId, 1);
    verify(sendStats).incLong(eq(closeCQSendDurationId), anyLong());
  }