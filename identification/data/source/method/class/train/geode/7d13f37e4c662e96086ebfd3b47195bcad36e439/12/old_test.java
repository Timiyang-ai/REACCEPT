  @Test
  public void endGetDurableCQsSend_FailedOperation() {
    int statId = ConnectionStats.getSendType().nameToId("getDurableCQsSendFailures");

    connectionStats.endGetDurableCQsSend(1, true);

    verify(sendStats).incInt(eq(getDurableCQsSendInProgressId), eq(-1));
    verify(sendStats).incInt(statId, 1);
    verify(sendStats).incLong(eq(getDurableCQsSendDurationId), anyLong());
  }