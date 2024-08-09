  @Test
  public void enhanceLogEntry_AddSampledSpanToLogEntry() {
    LogEntry logEntry =
        getEnhancedLogEntry(
            new OpenCensusTraceLoggingEnhancer("my-test-project-3"),
            new TestSpan(
                SpanContext.create(
                    TraceId.fromLowerBase16("4c6af40c499951eb7de2777ba1e4fefa"),
                    SpanId.fromLowerBase16("de52e84d13dd232d"),
                    TraceOptions.builder().setIsSampled(true).build(),
                    EMPTY_TRACESTATE)));
    assertTrue(logEntry.getTraceSampled());
    assertThat(logEntry.getTrace())
        .isEqualTo("projects/my-test-project-3/traces/4c6af40c499951eb7de2777ba1e4fefa");
    assertThat(logEntry.getSpanId()).isEqualTo("de52e84d13dd232d");
  }