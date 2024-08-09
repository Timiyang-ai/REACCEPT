public SpanId startNewSpan(String component, String operation) {
        SpanId spanId = startNewSpan(component, operation, clock().currentTimeMicroseconds());
        if (spanId == null) return null;
        Span span = spanAndEndpoint().span();
        synchronized (span) {
            span.startTick = System.nanoTime(); // embezzle start tick into an internal field.
        }
        return spanId;
    }