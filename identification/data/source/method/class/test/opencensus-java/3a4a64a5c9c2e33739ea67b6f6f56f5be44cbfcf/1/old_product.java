@Override
  public void enhanceLogEntry(LogEntry.Builder builder) {
    SpanContext span = tracer.getCurrentSpan().getContext();
    builder.setTrace(formatTraceId(span.getTraceId()));
    builder.setSpanId(span.getSpanId().toLowerBase16());

    // TODO(sebright): Find the correct way to add the sampling decision.
    builder.addLabel(SAMPLED_KEY, Boolean.toString(span.getTraceOptions().isSampled()));
  }