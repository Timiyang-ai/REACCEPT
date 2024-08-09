  @Test
  public void getExpirationDelay_empty() {
    when(cache.evictEntry(any(), any(), anyLong())).thenReturn(true);
    timerWheel.nanos = NOW;

    assertThat(timerWheel.getExpirationDelay(), is(Long.MAX_VALUE));
  }