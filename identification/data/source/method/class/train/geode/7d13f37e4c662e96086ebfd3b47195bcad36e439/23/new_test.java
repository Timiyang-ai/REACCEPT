  @Test
  public void endClearSend_FailedOperation() {
    int statId = ConnectionStats.getSendType().nameToId("clearSendFailures");

    connectionStats.endClearSend(1, true);

    verify(sendStats).incInt(eq(clearSendInProgressId), eq(-1));
    verify(sendStats).incInt(statId, 1);
    verify(sendStats).incLong(eq(clearSendDurationId), anyLong());
  }