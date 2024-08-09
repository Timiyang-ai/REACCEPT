@Override
  public void enhanceLogEntry(LogEntry.Builder builder) {
    switch (spanSelection) {
      case NO_SPANS:
        return;
      case SAMPLED_SPANS:
        SpanContext span = getCurrentSpanContext();
        if (span.getTraceOptions().isSampled()) {
          addTracingData(tracePrefix, span, builder);
        }
        return;
      case ALL_SPANS:
        addTracingData(tracePrefix, getCurrentSpanContext(), builder);
        return;
    }
    throw new AssertionError("Unknown spanSelection: " + spanSelection);
  }