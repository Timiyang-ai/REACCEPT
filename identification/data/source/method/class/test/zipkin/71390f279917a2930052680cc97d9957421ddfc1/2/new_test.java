  @Test public void filterTraces_skipsOnNoClash() {
    Span oneOne = Span.newBuilder().traceId(1, 1).id(1).build();
    Span oneTwo = Span.newBuilder().traceId(1, 2).id(1).build();
    List<List<Span>> traces = asList(asList(oneOne), asList(oneTwo));

    assertThat(StrictTraceId.filterTraces(
      requestBuilder().spanName("11").build()
    ).map(traces)).isSameAs(traces);
  }