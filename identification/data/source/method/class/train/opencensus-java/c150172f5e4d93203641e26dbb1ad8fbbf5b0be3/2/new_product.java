public SpanData toSpanData() {
    synchronized (this) {
      SpanData.Attributes attributesSpanData =
          attributes == null
              ? SpanData.Attributes.create(Collections.<String, AttributeValue>emptyMap(), 0)
              : SpanData.Attributes.create(attributes, attributes.getNumberOfDroppedAttributes());
      SpanData.TimedEvents<Annotation> annotationsSpanData =
          createTimedEvents(getInitializedAnnotations(), timestampConverter);
      SpanData.TimedEvents<io.opencensus.trace.MessageEvent> messageEventsSpanData =
          createTimedEvents(getInitializedNetworkEvents(), timestampConverter);
      SpanData.Links linksSpanData =
          links == null
              ? SpanData.Links.create(Collections.<Link>emptyList(), 0)
              : SpanData.Links.create(
                  new ArrayList<Link>(links.events), links.getNumberOfDroppedEvents());
      return SpanData.create(
          getContext(),
          parentSpanId,
          hasRemoteParent,
          name,
          kind,
          timestampConverter.convertNanoTime(startNanoTime),
          attributesSpanData,
          annotationsSpanData,
          messageEventsSpanData,
          linksSpanData,
          numberOfChildren,
          hasBeenEnded ? getStatusWithDefault() : null,
          hasBeenEnded ? timestampConverter.convertNanoTime(endNanoTime) : null);
    }
  }