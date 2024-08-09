  @Test
  public void endPrimaryAckSend_FailedOperation() {
    int statId = ConnectionStats.getSendType().nameToId("primaryAckSendFailures");

    connectionStats.endPrimaryAckSend(1, true);

    verify(sendStats).incInt(eq(primaryAckSendInProgressId), eq(-1));
    verify(sendStats).incInt(statId, 1);
    verify(sendStats).incLong(eq(primaryAckSendDurationId), anyLong());
  }