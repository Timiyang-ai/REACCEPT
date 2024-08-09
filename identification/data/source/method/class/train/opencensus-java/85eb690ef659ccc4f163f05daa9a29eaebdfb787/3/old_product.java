public boolean isValid() {
    return traceId.isValid() && spanId != INVALID_SPAN_ID;
  }