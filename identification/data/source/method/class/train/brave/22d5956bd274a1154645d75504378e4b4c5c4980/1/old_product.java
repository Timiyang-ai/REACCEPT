public Sampler toSampler(M method, Sampler fallback) {
    if (method == null) throw new NullPointerException("method == null");
    if (fallback == null) throw new NullPointerException("fallback == null");
    return new Sampler() {
      @Override public boolean isSampled(long traceId) {
        Boolean decision = sample(method).sampled();
        if (decision == null) return fallback.isSampled(traceId);
        return decision;
      }
    };
  }