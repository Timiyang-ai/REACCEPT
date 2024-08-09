  @Test
  public void endPutSend_FailedOperation() {
    int statId = ConnectionStats.getSendType().nameToId("putSendFailures");

    connectionStats.endPutSend(1, true);

    verify(sendStats).incInt(eq(putSendInProgressId), eq(-1));
    verify(sendStats).incInt(statId, 1);
    verify(sendStats).incLong(eq(putSendDurationId), anyLong());
  }