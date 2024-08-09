public Span nextSpan(Req request) {
    if (request == null) throw new NullPointerException("request == null");
    // nextSpan can be called independently when interceptors control lifecycle directly. In these
    // cases, it is possible to have HttpClientRequest as an argument.
    if (request instanceof HttpClientRequest) {
      HttpClientRequest clientRequest = (HttpClientRequest) request;
      return nextClientSpan(new HttpClientRequest.Adapter(clientRequest), clientRequest.unwrap());
    }
    return nextClientSpan(adapter, request);
  }