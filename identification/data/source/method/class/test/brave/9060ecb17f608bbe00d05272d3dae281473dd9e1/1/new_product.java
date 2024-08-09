public <C> Span handleReceive(TraceContext.Extractor<C> extractor, C carrier, Req request) {
    TraceContextOrSamplingFlags contextOrFlags = extractor.extract(carrier);
    Span span = contextOrFlags.context() != null
        ? tracer.joinSpan(contextOrFlags.context())
        : tracer.newTrace(contextOrFlags.samplingFlags());
    if (span.isNoop()) return span;

    // all of the parsing here occur before a timestamp is recorded on the span
    span.kind(Span.Kind.SERVER).name(parser.spanName(adapter, request));
    parser.requestTags(adapter, request, span);
    Endpoint.Builder remoteEndpoint = Endpoint.builder();
    if (adapter.parseClientAddress(request, remoteEndpoint)) {
      span.remoteEndpoint(remoteEndpoint.serviceName("").build());
    }
    return span.start();
  }