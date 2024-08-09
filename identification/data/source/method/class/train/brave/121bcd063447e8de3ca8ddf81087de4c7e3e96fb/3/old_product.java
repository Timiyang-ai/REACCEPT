Span nextSpan(HttpClientRequest.Adapter adapter) {
    Sampler override = httpSampler.toSampler(adapter, adapter.unwrapped, sampler);
    return tracer.withSampler(override).nextSpan();
  }