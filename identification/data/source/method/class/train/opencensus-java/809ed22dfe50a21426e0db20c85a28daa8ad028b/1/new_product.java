static Span toSpanProto(SpanData spanData) {
    SpanContext spanContext = spanData.getContext();
    TraceId traceId = spanContext.getTraceId();
    SpanId spanId = spanContext.getSpanId();
    Span.Builder spanBuilder =
        Span.newBuilder()
            .setTraceId(toByteString(traceId.getBytes()))
            .setSpanId(toByteString(spanId.getBytes()))
            .setTracestate(toTracestateProto(spanContext.getTracestate()))
            .setName(toTruncatableStringProto(spanData.getName()))
            .setStartTime(toTimestampProto(spanData.getStartTimestamp()))
            .setAttributes(toAttributesProto(spanData.getAttributes()))
            .setTimeEvents(
                toTimeEventsProto(spanData.getAnnotations(), spanData.getMessageEvents()))
            .setLinks(toLinksProto(spanData.getLinks()));

    Kind kind = spanData.getKind();
    if (kind != null) {
      spanBuilder.setKind(toSpanKindProto(kind));
    }

    io.opencensus.trace.Status status = spanData.getStatus();
    if (status != null) {
      spanBuilder.setStatus(toStatusProto(status));
    }

    Timestamp end = spanData.getEndTimestamp();
    if (end != null) {
      spanBuilder.setEndTime(toTimestampProto(end));
    }

    Integer childSpanCount = spanData.getChildSpanCount();
    if (childSpanCount != null) {
      spanBuilder.setChildSpanCount(UInt32Value.newBuilder().setValue(childSpanCount).build());
    }

    Boolean hasRemoteParent = spanData.getHasRemoteParent();
    if (hasRemoteParent != null) {
      spanBuilder.setSameProcessAsParentSpan(BoolValue.of(!hasRemoteParent));
    }

    SpanId parentSpanId = spanData.getParentSpanId();
    if (parentSpanId != null && parentSpanId.isValid()) {
      spanBuilder.setParentSpanId(toByteString(parentSpanId.getBytes()));
    }

    return spanBuilder.build();
  }