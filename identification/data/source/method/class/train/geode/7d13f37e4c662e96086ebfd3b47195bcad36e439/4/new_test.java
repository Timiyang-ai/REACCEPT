  @Test
  public void endExecuteFunctionSend_FailedOperation() {
    int statId = ConnectionStats.getSendType().nameToId("executeFunctionSendFailures");

    connectionStats.endExecuteFunctionSend(1, true);

    verify(sendStats).incInt(eq(executeFunctionSendInProgressId), eq(-1));
    verify(sendStats).incInt(statId, 1);
    verify(sendStats).incLong(eq(executeFunctionSendDurationId), anyLong());
  }