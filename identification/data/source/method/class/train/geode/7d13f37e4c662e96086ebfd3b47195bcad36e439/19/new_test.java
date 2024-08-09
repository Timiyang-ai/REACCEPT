  @Test
  public void endRemoveAllSend_FailedOperation() {
    int statId = ConnectionStats.getSendType().nameToId("removeAllSendFailures");

    connectionStats.endRemoveAllSend(1, true);

    verify(sendStats).incInt(eq(removeAllSendInProgressId), eq(-1));
    verify(sendStats).incInt(statId, 1);
    verify(sendStats).incLong(eq(removeAllSendDurationId), anyLong());
  }