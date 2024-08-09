  @Test
  public void toSpanProto() {
    SpanData spanData =
        SpanData.create(
            spanContext,
            parentSpanId,
            /* hasRemoteParent= */ false,
            SPAN_NAME,
            Kind.CLIENT,
            startTimestamp,
            attributes,
            annotations,
            messageEvents,
            links,
            CHILD_SPAN_COUNT,
            status,
            endTimestamp);
    TimeEvent annotationTimeEvent1 =
        TimeEvent.newBuilder()
            .setAnnotation(
                TimeEvent.Annotation.newBuilder()
                    .setDescription(toTruncatableStringProto(ANNOTATION_TEXT))
                    .setAttributes(Span.Attributes.newBuilder().build())
                    .build())
            .setTime(
                com.google.protobuf.Timestamp.newBuilder()
                    .setSeconds(eventTimestamp1.getSeconds())
                    .setNanos(eventTimestamp1.getNanos())
                    .build())
            .build();
    TimeEvent annotationTimeEvent2 =
        TimeEvent.newBuilder()
            .setAnnotation(
                TimeEvent.Annotation.newBuilder()
                    .setDescription(toTruncatableStringProto(ANNOTATION_TEXT))
                    .setAttributes(Span.Attributes.newBuilder().build())
                    .build())
            .setTime(
                com.google.protobuf.Timestamp.newBuilder()
                    .setSeconds(eventTimestamp3.getSeconds())
                    .setNanos(eventTimestamp3.getNanos())
                    .build())
            .build();

    TimeEvent sentTimeEvent =
        TimeEvent.newBuilder()
            .setMessageEvent(
                TimeEvent.MessageEvent.newBuilder()
                    .setType(MessageEvent.Type.SENT)
                    .setId(sentMessageEvent.getMessageId()))
            .setTime(
                com.google.protobuf.Timestamp.newBuilder()
                    .setSeconds(eventTimestamp2.getSeconds())
                    .setNanos(eventTimestamp2.getNanos())
                    .build())
            .build();
    TimeEvent recvTimeEvent =
        TimeEvent.newBuilder()
            .setMessageEvent(
                TimeEvent.MessageEvent.newBuilder()
                    .setType(MessageEvent.Type.RECEIVED)
                    .setId(recvMessageEvent.getMessageId()))
            .setTime(
                com.google.protobuf.Timestamp.newBuilder()
                    .setSeconds(eventTimestamp1.getSeconds())
                    .setNanos(eventTimestamp1.getNanos())
                    .build())
            .build();

    Span.Links spanLinks =
        Span.Links.newBuilder()
            .setDroppedLinksCount(DROPPED_LINKS_COUNT)
            .addLink(
                Span.Link.newBuilder()
                    .setType(Span.Link.Type.CHILD_LINKED_SPAN)
                    .setTraceId(toByteString(traceId.getBytes()))
                    .setSpanId(toByteString(spanId.getBytes()))
                    .setAttributes(Span.Attributes.newBuilder().build())
                    .build())
            .build();

    io.opencensus.proto.trace.v1.Status spanStatus =
        io.opencensus.proto.trace.v1.Status.newBuilder()
            .setCode(com.google.rpc.Code.DEADLINE_EXCEEDED.getNumber())
            .setMessage("TooSlow")
            .build();

    com.google.protobuf.Timestamp startTime =
        com.google.protobuf.Timestamp.newBuilder()
            .setSeconds(startTimestamp.getSeconds())
            .setNanos(startTimestamp.getNanos())
            .build();
    com.google.protobuf.Timestamp endTime =
        com.google.protobuf.Timestamp.newBuilder()
            .setSeconds(endTimestamp.getSeconds())
            .setNanos(endTimestamp.getNanos())
            .build();

    Span span = TraceProtoUtils.toSpanProto(spanData);
    assertThat(span.getName()).isEqualTo(toTruncatableStringProto(SPAN_NAME));
    assertThat(span.getTraceId()).isEqualTo(toByteString(traceId.getBytes()));
    assertThat(span.getSpanId()).isEqualTo(toByteString(spanId.getBytes()));
    assertThat(span.getParentSpanId()).isEqualTo(toByteString(parentSpanId.getBytes()));
    assertThat(span.getStartTime()).isEqualTo(startTime);
    assertThat(span.getEndTime()).isEqualTo(endTime);
    assertThat(span.getKind()).isEqualTo(SpanKind.CLIENT);
    assertThat(span.getAttributes().getDroppedAttributesCount())
        .isEqualTo(DROPPED_ATTRIBUTES_COUNT);
    // The generated attributes map contains more values (e.g. agent). We only test what we added.
    assertThat(span.getAttributes().getAttributeMapMap())
        .containsEntry(ATTRIBUTE_KEY_1, AttributeValue.newBuilder().setIntValue(10L).build());
    assertThat(span.getAttributes().getAttributeMapMap())
        .containsEntry(ATTRIBUTE_KEY_2, AttributeValue.newBuilder().setBoolValue(true).build());
    assertThat(span.getTimeEvents().getDroppedMessageEventsCount())
        .isEqualTo(DROPPED_NETWORKEVENTS_COUNT);
    assertThat(span.getTimeEvents().getDroppedAnnotationsCount())
        .isEqualTo(DROPPED_ANNOTATIONS_COUNT);
    assertThat(span.getTimeEvents().getTimeEventList())
        .containsExactly(annotationTimeEvent1, annotationTimeEvent2, sentTimeEvent, recvTimeEvent);
    assertThat(span.getLinks()).isEqualTo(spanLinks);
    assertThat(span.getStatus()).isEqualTo(spanStatus);
    assertThat(span.getSameProcessAsParentSpan()).isEqualTo(BoolValue.of(true));
    assertThat(span.getChildSpanCount())
        .isEqualTo(UInt32Value.newBuilder().setValue(CHILD_SPAN_COUNT).build());
  }