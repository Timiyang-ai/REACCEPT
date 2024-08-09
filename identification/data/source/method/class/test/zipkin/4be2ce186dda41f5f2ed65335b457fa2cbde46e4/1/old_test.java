  @Test
  public void storeSpans_readbackRaw() {
    Span missingDuration = LOTS_OF_SPANS[0].toBuilder().duration(null).build();
    Span withDuration = LOTS_OF_SPANS[0];

    // write the span to zipkin directly
    zipkin.storeSpans(asList(missingDuration));
    zipkin.storeSpans(asList(withDuration));

    assertThat(zipkin.getTrace(missingDuration.traceId()))
      .containsExactly(missingDuration, withDuration);
  }