public Span nextSpan(Req request) {
    // nextSpan can be called independently when interceptors control lifecycle directly. In these
    // cases, it is possible to have HttpClientRequest as an argument.
    if (request instanceof HttpClientRequest) {
      return nextSpan(new HttpClientRequest.Adapter((HttpClientRequest) request));
    }
    Sampler override = httpSampler.toSampler(adapter, request, sampler);
    return tracer.withSampler(override).nextSpan();
  }