public SpanId startNewSpan(String component, String operation, long timestamp) {
        Span span = newSpan();
        if (span == null) return null;
        return startSpan(component, operation, timestamp, span);
    }