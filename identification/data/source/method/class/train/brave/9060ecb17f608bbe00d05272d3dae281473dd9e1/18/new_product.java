@Deprecated public Span handleSend(Injector<Req> injector, Req request, Span span) {
    return handleSend(injector, request, request, span);
  }