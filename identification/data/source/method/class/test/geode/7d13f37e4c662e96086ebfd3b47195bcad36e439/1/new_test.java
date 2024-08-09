  @Test
  public void endRegisterInstantiatorsSend_FailedOperation() {
    int statId = ConnectionStats.getSendType().nameToId("registerInstantiatorsSendFailures");

    connectionStats.endRegisterInstantiatorsSend(1, true);

    verify(sendStats).incInt(eq(registerInstantiatorsSendInProgressId), eq(-1));
    verify(sendStats).incInt(statId, 1);
    verify(sendStats).incLong(eq(registerInstantiatorsSendDurationId), anyLong());
  }