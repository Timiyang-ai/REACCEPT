  @Test
  public void endQuerySend_FailedOperation() {
    int statId = ConnectionStats.getSendType().nameToId("querySendFailures");

    connectionStats.endQuerySend(1, true);

    verify(sendStats).incInt(eq(querySendInProgressId), eq(-1));
    verify(sendStats).incInt(statId, 1);
    verify(sendStats).incLong(eq(querySendDurationId), anyLong());
  }