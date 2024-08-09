  @Test
  public void endDestroyRegionSend_FailedOperation() {
    int statId = ConnectionStats.getSendType().nameToId("destroyRegionSendFailures");

    connectionStats.endDestroyRegionSend(1, true);

    verify(sendStats).incInt(eq(destroyRegionSendInProgressId), eq(-1));
    verify(sendStats).incInt(statId, 1);
    verify(sendStats).incLong(eq(destroyRegionSendDurationId), anyLong());
  }