  @Test
  public void endGetAllSend_FailedOperation() {
    int statId = ConnectionStats.getSendType().nameToId("getAllSendFailures");

    connectionStats.endGetAllSend(1, true);

    verify(sendStats).incInt(eq(getAllSendInProgressId), eq(-1));
    verify(sendStats).incInt(statId, 1);
    verify(sendStats).incLong(eq(getAllSendDurationId), anyLong());
  }