@Override
  public void enhanceLogEntry(LogEntry.Builder builder) {
    switch (spanSelection) {
      case NO_SPANS:
        return;
      case SAMPLED_SPANS:
        SpanContext span = tracer.getCurrentSpan().getContext();
        if (span.getTraceOptions().isSampled()) {
          addTracingData(span, builder);
        }
        return;
      case ALL_SPANS:
        addTracingData(tracer.getCurrentSpan().getContext(), builder);
        return;
    }
    throw new AssertionError("Unknown spanSelection: " + spanSelection);
  }