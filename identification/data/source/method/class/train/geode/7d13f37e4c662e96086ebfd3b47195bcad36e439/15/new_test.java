  @Test
  public void endContainsKeySend_FailedOperation() {
    int statId = ConnectionStats.getSendType().nameToId("containsKeySendFailures");

    connectionStats.endContainsKeySend(1, true);

    verify(sendStats).incInt(eq(containsKeySendInProgressId), eq(-1));
    verify(sendStats).incInt(statId, 1);
    verify(sendStats).incLong(eq(containsKeySendDurationId), anyLong());
  }