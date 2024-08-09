  @Test
  public void endClear_TimeoutOperation() {
    int statId = ConnectionStats.getType().nameToId("clearTimeouts");

    connectionStats.endClear(1, true, true);

    verify(stats).incInt(eq(clearInProgressId), eq(-1));
    verify(stats).incLong(statId, 1L);
    verify(stats).incLong(eq(clearDurationId), anyLong());
  }