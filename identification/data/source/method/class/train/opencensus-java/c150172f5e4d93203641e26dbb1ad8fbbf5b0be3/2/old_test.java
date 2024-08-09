  @Test
  public void toSpanData_ActiveSpan() {
    RecordEventsSpanImpl span =
        RecordEventsSpanImpl.startSpan(
            spanContext,
            SPAN_NAME,
            null,
            parentSpanId,
            true,
            TraceParams.DEFAULT,
            startEndHandler,
            timestampConverter,
            testClock);
    Mockito.verify(startEndHandler, Mockito.times(1)).onStart(span);
    span.putAttribute(
        "MySingleStringAttributeKey",
        AttributeValue.stringAttributeValue("MySingleStringAttributeValue"));
    span.putAttributes(attributes);
    testClock.advanceTime(Duration.create(0, 100));
    span.addAnnotation(Annotation.fromDescription(ANNOTATION_DESCRIPTION));
    testClock.advanceTime(Duration.create(0, 100));
    span.addAnnotation(ANNOTATION_DESCRIPTION, attributes);
    testClock.advanceTime(Duration.create(0, 100));
    NetworkEvent networkEvent =
        NetworkEvent.builder(NetworkEvent.Type.RECV, 1).setUncompressedMessageSize(3).build();
    span.addNetworkEvent(networkEvent);
    testClock.advanceTime(Duration.create(0, 100));
    Link link = Link.fromSpanContext(spanContext, Link.Type.CHILD_LINKED_SPAN);
    span.addLink(link);
    SpanData spanData = span.toSpanData();
    assertThat(spanData.getContext()).isEqualTo(spanContext);
    assertThat(spanData.getName()).isEqualTo(SPAN_NAME);
    assertThat(spanData.getParentSpanId()).isEqualTo(parentSpanId);
    assertThat(spanData.getHasRemoteParent()).isTrue();
    assertThat(spanData.getAttributes().getDroppedAttributesCount()).isEqualTo(0);
    assertThat(spanData.getAttributes().getAttributeMap()).isEqualTo(expectedAttributes);
    assertThat(spanData.getAnnotations().getDroppedEventsCount()).isEqualTo(0);
    assertThat(spanData.getAnnotations().getEvents().size()).isEqualTo(2);
    assertThat(spanData.getAnnotations().getEvents().get(0).getTimestamp())
        .isEqualTo(timestamp.addNanos(100));
    assertThat(spanData.getAnnotations().getEvents().get(0).getEvent())
        .isEqualTo(Annotation.fromDescription(ANNOTATION_DESCRIPTION));
    assertThat(spanData.getAnnotations().getEvents().get(1).getTimestamp())
        .isEqualTo(timestamp.addNanos(200));
    assertThat(spanData.getAnnotations().getEvents().get(1).getEvent())
        .isEqualTo(Annotation.fromDescriptionAndAttributes(ANNOTATION_DESCRIPTION, attributes));
    assertThat(spanData.getNetworkEvents().getDroppedEventsCount()).isEqualTo(0);
    assertThat(spanData.getNetworkEvents().getEvents().size()).isEqualTo(1);
    assertThat(spanData.getNetworkEvents().getEvents().get(0).getTimestamp())
        .isEqualTo(timestamp.addNanos(300));
    assertThat(spanData.getNetworkEvents().getEvents().get(0).getEvent()).isEqualTo(networkEvent);
    assertThat(spanData.getLinks().getDroppedLinksCount()).isEqualTo(0);
    assertThat(spanData.getLinks().getLinks().size()).isEqualTo(1);
    assertThat(spanData.getLinks().getLinks().get(0)).isEqualTo(link);
    assertThat(spanData.getStartTimestamp()).isEqualTo(timestamp);
    assertThat(spanData.getStatus()).isNull();
    assertThat(spanData.getEndTimestamp()).isNull();
  }