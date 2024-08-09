  @Test
  public void endCloseConSend_FailedOperation() {
    int statId = ConnectionStats.getSendType().nameToId("closeConSendFailures");

    connectionStats.endCloseConSend(1, true);

    verify(sendStats).incInt(eq(closeConSendInProgressId), eq(-1));
    verify(sendStats).incInt(statId, 1);
    verify(sendStats).incLong(eq(closeConSendDurationId), anyLong());
  }