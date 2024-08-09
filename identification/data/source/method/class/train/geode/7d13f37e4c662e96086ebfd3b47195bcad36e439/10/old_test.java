  @Test
  public void endDestroySend_FailedOperation() {
    int statId = ConnectionStats.getSendType().nameToId("destroySendFailures");

    connectionStats.endDestroySend(1, true);

    verify(sendStats).incInt(eq(destroySendInProgressId), eq(-1));
    verify(sendStats).incInt(statId, 1);
    verify(sendStats).incLong(eq(destroySendDurationId), anyLong());
  }