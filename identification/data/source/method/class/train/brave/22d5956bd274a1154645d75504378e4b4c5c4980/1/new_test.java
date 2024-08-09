  @Test public void toSampler() {
    assertThat(declarativeSampler.toSampler(traced(1.0f, 0, true)).isSampled(0L))
      .isTrue();

    assertThat(declarativeSampler.toSampler(traced(0.0f, 0, true)).isSampled(0L))
      .isFalse();

    // check not enabled is false
    assertThat(declarativeSampler.toSampler(traced(1.0f, 0, false)).isSampled(0L))
      .isFalse();
  }