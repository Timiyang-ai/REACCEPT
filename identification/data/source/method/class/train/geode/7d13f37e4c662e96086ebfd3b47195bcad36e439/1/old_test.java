  @Test
  public void endMakePrimarySend_FailedOperation() {
    int statId = ConnectionStats.getSendType().nameToId("makePrimarySendFailures");

    connectionStats.endMakePrimarySend(1, true);

    verify(sendStats).incInt(eq(makePrimarySendInProgressId), eq(-1));
    verify(sendStats).incInt(statId, 1);
    verify(sendStats).incLong(eq(makePrimarySendDurationId), anyLong());
  }