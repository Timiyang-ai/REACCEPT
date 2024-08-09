  @Test
  public void endGetClientPartitionAttributesSend_FailedOperation() {
    int statId = ConnectionStats.getSendType().nameToId("getClientPartitionAttributesSendFailures");

    connectionStats.endGetClientPartitionAttributesSend(1, true);

    verify(sendStats).incInt(eq(getClientPartitionAttributesSendInProgressId), eq(-1));
    verify(sendStats).incInt(statId, 1);
    verify(sendStats).incLong(eq(getClientPartitionAttributesSendDurationId), anyLong());
  }