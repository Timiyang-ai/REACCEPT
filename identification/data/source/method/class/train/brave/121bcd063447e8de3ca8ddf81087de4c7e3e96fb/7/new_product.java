public Span nextSpan(Req request) {
    Sampler override = httpSampler.toSampler(adapter, request, sampler);
    return tracer.withSampler(override).nextSpan();
  }