public SpanId startNewSpan(String component, String operation) {
        return startNewSpan(component, operation, clock().currentTimeMicroseconds());
    }