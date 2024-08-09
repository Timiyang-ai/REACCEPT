  @Test public void addRule() {
    HttpRuleSampler sampler = HttpRuleSampler.newBuilder()
      .addRule("GET", "/foo", 0.0f)
      .build();

    when(httpServerRequest.method()).thenReturn("POST");

    assertThat(sampler.trySample(httpServerRequest))
      .isNull();

    when(httpServerRequest.method()).thenReturn("GET");
    when(httpServerRequest.path()).thenReturn("/foo");

    assertThat(sampler.trySample(httpServerRequest))
      .isFalse();
  }