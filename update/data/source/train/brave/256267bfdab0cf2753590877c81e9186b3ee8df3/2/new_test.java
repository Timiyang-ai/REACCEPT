@Test
  public void setClientReceived_lessThanMicrosRoundUp() {
    recorder.start(span, START_TIME_MICROSECONDS);
    brave.clientSpanThreadBinder().setCurrentSpan(span);

    timestamp = START_TIME_MICROSECONDS; // no time passed!

    brave.clientTracer().setClientReceived();

    assertThat(spans.get(0).duration).isEqualTo(1L);
  }