  @Test
  public void endUnregisterInterestSend_FailedOperation() {
    int statId = ConnectionStats.getSendType().nameToId("unregisterInterestSendFailures");

    connectionStats.endUnregisterInterestSend(1, true);

    verify(sendStats).incInt(eq(unregisterInterestSendInProgressId), eq(-1));
    verify(sendStats).incInt(statId, 1);
    verify(sendStats).incLong(eq(unregisterInterestSendDurationId), anyLong());
  }