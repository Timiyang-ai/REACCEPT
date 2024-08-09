@Deprecated public Builder addRule(@Nullable String method, String path, float probability) {
      if (path == null) throw new NullPointerException("path == null");
      Sampler sampler = CountingSampler.create(probability);
      if (method == null) {
        delegate.putRule(pathStartsWith(path), RateLimitingSampler.create(10));
        return this;
      }
      delegate.putRule(and(methodEquals(method), pathStartsWith(path)), sampler);
      return this;
    }