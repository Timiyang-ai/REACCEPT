@Test
  public void setServerSend_lessThanMicrosRoundUp() {
    recorder.start(span, START_TIME_MICROSECONDS);
    brave.serverSpanThreadBinder().setCurrentSpan(serverSpan);

    timestamp = START_TIME_MICROSECONDS; // no time passed!

    brave.serverTracer().setServerSend();

    assertThat(spans.get(0).duration).isEqualTo(1L);
  }