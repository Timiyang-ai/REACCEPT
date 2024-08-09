@Deprecated public Sampler toSampler(M method, Sampler fallback) {
    if (fallback == null) throw new NullPointerException("fallback == null");
    if (method == null) return fallback;
    return new Sampler() {
      @Override public boolean isSampled(long traceId) {
        Boolean decision = trySample(method);
        if (decision == null) return fallback.isSampled(traceId);
        return decision;
      }
    };
  }