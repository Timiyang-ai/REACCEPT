  @Test
  public void endGetSend_FailedOperation() {
    int statId = ConnectionStats.getSendType().nameToId("getSendFailures");

    connectionStats.endGetSend(1, true);

    verify(sendStats).incInt(eq(getSendInProgressId), eq(-1));
    verify(sendStats).incInt(statId, 1);
    verify(sendStats).incLong(eq(getSendDurationId), anyLong());
  }