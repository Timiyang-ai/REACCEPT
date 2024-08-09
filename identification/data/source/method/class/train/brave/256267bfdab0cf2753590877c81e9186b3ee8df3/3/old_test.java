@Test
  public void setClientReceived_lessThanMicrosRoundUp() {
    recorder.start(span, START_TIME_MICROSECONDS);
    brave.clientSpanThreadBinder().setCurrentSpan(span);

    PowerMockito.when(System.nanoTime()).thenReturn(500L);

    brave.clientTracer().setClientReceived();

    assertThat(spans.get(0).duration).isEqualTo(1L);
  }