<Req1> Span nextClientSpan(HttpClientAdapter<Req1, ?> adapter, Req1 req1) {
    Tracer scoped = samplingTracer != null ? samplingTracer : tracer.withSampler(new Sampler() {
      @Override public boolean isSampled(long traceId) {
        Boolean decision = httpSampler.trySample(adapter, req1);
        if (decision == null) return sampler.isSampled(traceId);
        return decision;
      }
    });
    return scoped.nextSpan();
  }