@Override
  public void enhanceLogEntry(LogEntry.Builder builder) {
    addTracingData(tracePrefix, getCurrentSpanContext(), builder);
  }