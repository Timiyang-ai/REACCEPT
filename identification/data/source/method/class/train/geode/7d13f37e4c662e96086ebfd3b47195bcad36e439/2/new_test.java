  @Test
  public void endRegisterInterestSend_FailedOperation() {
    int statId = ConnectionStats.getSendType().nameToId("registerInterestSendFailures");

    connectionStats.endRegisterInterestSend(1, true);

    verify(sendStats).incInt(eq(registerInterestSendInProgressId), eq(-1));
    verify(sendStats).incInt(statId, 1);
    verify(sendStats).incLong(eq(registerInterestSendDurationId), anyLong());
  }