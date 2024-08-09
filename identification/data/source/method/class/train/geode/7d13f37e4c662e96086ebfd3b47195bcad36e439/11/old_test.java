  @Test
  public void endKeySetSend_FailedOperation() {
    int statId = ConnectionStats.getSendType().nameToId("keySetSendFailures");

    connectionStats.endKeySetSend(1, true);

    verify(sendStats).incInt(eq(keySetSendInProgressId), eq(-1));
    verify(sendStats).incInt(statId, 1);
    verify(sendStats).incLong(eq(keySetSendDurationId), anyLong());
  }