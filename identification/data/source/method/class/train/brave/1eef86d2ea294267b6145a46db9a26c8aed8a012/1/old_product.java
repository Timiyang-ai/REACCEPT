public SpanId startNewSpan(@Nullable String requestName) {
        // When a trace context is extracted from an incoming request, it may have only the
        // sampled header (no ids). If the header says unsampled, we must honor that. Since
        // we currently don't synthesize a fake span when a trace is unsampled, we have to
        // check sampled state explicitly.
        Boolean sample = currentServerSpan().sampled();
        if (Boolean.FALSE.equals(sample)) {
            currentSpan().setCurrentSpan(null);
            return null;
        }

        SpanId nextContext = spanIdFactory().next(maybeParent());
        if (Boolean.FALSE.equals(nextContext.sampled())) {
            currentSpan().setCurrentSpan(null);
            return null;
        }

        Span newSpan = Brave.newSpan(nextContext).setName(requestName);
        currentSpan().setCurrentSpan(newSpan);
        return nextContext;
    }