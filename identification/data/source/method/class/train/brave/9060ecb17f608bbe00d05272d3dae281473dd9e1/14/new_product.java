public <C> Span handleSend(TraceContext.Injector<C> injector, C carrier, Req request) {
    Span span = tracer.nextSpan();
    injector.inject(span.context(), carrier);
    if (span.isNoop()) return span;

    // all of the parsing here occur before a timestamp is recorded on the span
    span.kind(Span.Kind.CLIENT).name(parser.spanName(adapter, request));
    parser.requestTags(adapter, request, span);
    Endpoint.Builder remoteEndpoint = Endpoint.builder();
    if (adapter.parseServerAddress(request, remoteEndpoint) || serverNameSet) {
      span.remoteEndpoint(remoteEndpoint.serviceName(serverName).build());
    }
    return span.start();
  }