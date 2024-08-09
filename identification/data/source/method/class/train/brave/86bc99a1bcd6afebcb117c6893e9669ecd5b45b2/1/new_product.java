public SpanId startNewSpan(String requestName) {

        Boolean sample = spanAndEndpoint().state().sample();
        if (Boolean.FALSE.equals(sample)) {
            spanAndEndpoint().state().setCurrentClientSpan(null);
            return null;
        }

        SpanId nextContext = nextContext(maybeParent());
        if (sample == null) {
            // No sample indication is present.
            if (!traceSampler().isSampled(nextContext.traceId)) {
                spanAndEndpoint().state().setCurrentClientSpan(null);
                return null;
            }
        }

        Span newSpan = Span.create(nextContext).setName(requestName);
        spanAndEndpoint().state().setCurrentClientSpan(newSpan);
        return nextContext;
    }