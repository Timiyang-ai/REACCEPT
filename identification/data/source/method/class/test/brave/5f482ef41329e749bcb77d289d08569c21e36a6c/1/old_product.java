public SpanId startNewSpan(String component, String operation, long timestamp) {
        // When a trace context is extracted from an incoming request, it may have only the
        // sampled header (no ids). If the header says unsampled, we must honor that. Since
        // we currently don't synthesize a fake span when a trace is unsampled, we have to
        // check sampled state explicitly.
        Boolean sample = currentServerSpan().sampled();
        if (Boolean.FALSE.equals(sample)) {
            currentSpan().setCurrentSpan(null);
            return null;
        }

        Span span = spanFactory().nextSpan(maybeParent());
        SpanId context = Brave.context(span);
        if (Boolean.FALSE.equals(context.sampled())) {
            currentSpan().setCurrentSpan(null);
            return null;
        }

        recorder().start(span, timestamp);
        recorder().name(span, operation);
        recorder().tag(span, LOCAL_COMPONENT, component);

        currentSpan().setCurrentSpan(span);
        return context;
    }