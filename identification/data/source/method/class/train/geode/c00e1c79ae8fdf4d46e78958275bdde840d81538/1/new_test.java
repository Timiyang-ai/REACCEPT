  @Test
  public void endDestroy_TimeoutOperation() {
    int statId = ConnectionStats.getType().nameToId("destroyTimeouts");

    connectionStats.endDestroy(1, true, true);

    verify(stats).incInt(eq(destroyInProgressId), eq(-1));
    verify(stats).incLong(statId, 1);
    verify(stats).incLong(eq(destroyDurationId), anyLong());
  }