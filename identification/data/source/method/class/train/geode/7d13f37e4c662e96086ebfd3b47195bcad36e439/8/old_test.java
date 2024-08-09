  @Test
  public void endPingSend_FailedOperation() {
    int statId = ConnectionStats.getSendType().nameToId("pingSendFailures");

    connectionStats.endPingSend(1, true);

    verify(sendStats).incInt(eq(pingSendInProgressId), eq(-1));
    verify(sendStats).incInt(statId, 1);
    verify(sendStats).incLong(eq(pingSendDurationId), anyLong());
  }