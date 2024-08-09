public SpanId startNewSpan(String component, String operation, long timestamp) {

        Boolean sample = spanAndEndpoint().state().sample();
        if (Boolean.FALSE.equals(sample)) {
            spanAndEndpoint().state().setCurrentLocalSpan(null);
            return null;
        }

        SpanId newSpanId = getNewSpanId();
        if (sample == null) {
            // No sample indication is present.
            if (!traceSampler().isSampled(newSpanId.getTraceId())) {
                spanAndEndpoint().state().setCurrentLocalSpan(null);
                return null;
            }
        }

        Span newSpan = new Span();
        newSpan.setId(newSpanId.getSpanId());
        newSpan.setTrace_id(newSpanId.getTraceId());
        if (newSpanId.getParentSpanId() != null) {
            newSpan.setParent_id(newSpanId.getParentSpanId());
        }
        newSpan.setName(operation);
        newSpan.setTimestamp(timestamp);
        newSpan.addToBinary_annotations(
            BinaryAnnotation.create(LOCAL_COMPONENT, component, spanAndEndpoint().endpoint()));
        spanAndEndpoint().state().setCurrentLocalSpan(newSpan);
        return newSpanId;
    }