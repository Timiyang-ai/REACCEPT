public SpanId startNewSpan(String requestName) {

        Boolean sample = spanAndEndpoint().state().sample();
        if (Boolean.FALSE.equals(sample)) {
            spanAndEndpoint().state().setCurrentClientSpan(null);
            return null;
        }

        SpanId newSpanId = getNewSpanId();
        if (sample == null) {
            // No sample indication is present.
            if (!traceSampler().isSampled(newSpanId.traceId)) {
                spanAndEndpoint().state().setCurrentClientSpan(null);
                return null;
            }
        }

        Span newSpan = Span.fromSpanId(newSpanId);
        newSpan.setName(requestName);
        spanAndEndpoint().state().setCurrentClientSpan(newSpan);
        return newSpanId;
    }