public Span handleSend(TraceContext.Injector<Req> injector, Req request, Span span) {
    return handleSend(injector, request, request, span);
  }