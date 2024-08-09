public SpanId startNewSpan(String component, String operation, long timestamp) {
        return startNewSpan(component, operation, (Long) timestamp);
    }