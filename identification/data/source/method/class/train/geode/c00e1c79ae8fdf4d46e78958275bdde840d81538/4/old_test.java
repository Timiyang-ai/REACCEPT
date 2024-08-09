  @Test
  public void endPut_TimeoutOperation() {
    int statId = ConnectionStats.getType().nameToId("putTimeouts");

    connectionStats.endPut(1, true, true);

    verify(stats).incInt(eq(putInProgressId), eq(-1));
    verify(stats).incLong(statId, 1L);
    verify(stats).incLong(eq(putDurationId), anyLong());
  }