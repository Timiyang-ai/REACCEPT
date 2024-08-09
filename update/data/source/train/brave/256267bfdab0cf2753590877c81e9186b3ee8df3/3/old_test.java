@Test
  public void setServerSend_lessThanMicrosRoundUp() {
    recorder.start(span, START_TIME_MICROSECONDS);
    brave.serverSpanThreadBinder().setCurrentSpan(serverSpan);

    PowerMockito.when(System.nanoTime()).thenReturn(500L);

    brave.serverTracer().setServerSend();

    assertThat(spans.get(0).duration).isEqualTo(1L);
  }