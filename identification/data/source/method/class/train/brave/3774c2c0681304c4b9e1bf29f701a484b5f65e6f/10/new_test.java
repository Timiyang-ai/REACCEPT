  @Test public void withSampler() {
    Sampler sampler = new Sampler() {
      @Override public boolean isSampled(long traceId) {
        return false;
      }
    };

    tracer = tracer.withSampler(sampler);

    assertThat(tracer.sampler)
      .isSameAs(sampler);
  }