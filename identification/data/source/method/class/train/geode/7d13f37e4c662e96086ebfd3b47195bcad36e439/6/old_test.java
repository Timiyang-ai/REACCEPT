  @Test
  public void endCreateCQSend_FailedOperation() {
    int statId = ConnectionStats.getSendType().nameToId("createCQSendFailures");

    connectionStats.endCreateCQSend(1, true);

    verify(sendStats).incInt(eq(createCQSendInProgressId), eq(-1));
    verify(sendStats).incInt(statId, 1);
    verify(sendStats).incLong(eq(createCQSendDurationId), anyLong());
  }