  @Test
  public void endGetClientPRMetadataSend_FailedOperation() {
    int statId = ConnectionStats.getSendType().nameToId("getClientPRMetadataSendFailures");

    connectionStats.endGetClientPRMetadataSend(1, true);

    verify(sendStats).incInt(eq(getClientPRMetadataSendInProgressId), eq(-1));
    verify(sendStats).incInt(statId, 1);
    verify(sendStats).incLong(eq(getClientPRMetadataSendDurationId), anyLong());
  }