  @Test
  public void endGet_TimeoutOperation() {
    int statId = ConnectionStats.getType().nameToId("getTimeouts");

    connectionStats.endGet(1, true, true);

    verify(stats).incInt(eq(getInProgressId), eq(-1));
    verify(stats).incLong(statId, 1L);
    verify(stats).incLong(eq(getDurationId), anyLong());
  }