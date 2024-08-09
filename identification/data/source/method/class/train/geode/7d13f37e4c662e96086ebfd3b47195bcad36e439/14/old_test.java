  @Test
  public void endReadyForEventsSend_FailedOperation() {
    int statId = ConnectionStats.getSendType().nameToId("readyForEventsSendFailures");

    connectionStats.endReadyForEventsSend(1, true);

    verify(sendStats).incInt(eq(readyForEventsSendInProgressId), eq(-1));
    verify(sendStats).incInt(statId, 1);
    verify(sendStats).incLong(eq(readyForEventsSendDurationId), anyLong());
  }