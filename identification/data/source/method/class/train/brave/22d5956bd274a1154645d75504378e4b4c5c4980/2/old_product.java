public Sampler toSampler(M method) {
    if (method == null) throw new NullPointerException("method == null");
    return new Sampler() {
      @Override public boolean isSampled(long traceId) {
        Boolean decision = sample(method).sampled();
        return decision != null ? decision : false;
      }
    };
  }