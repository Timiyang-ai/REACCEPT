  @Test public void toSamplingFlags_returnsConstants() {
    assertThat(toSamplingFlags(SamplingFlags.EMPTY.flags))
      .isSameAs(SamplingFlags.EMPTY);
    assertThat(toSamplingFlags(SamplingFlags.NOT_SAMPLED.flags))
      .isSameAs(SamplingFlags.NOT_SAMPLED);
    assertThat(toSamplingFlags(SamplingFlags.SAMPLED.flags))
      .isSameAs(SamplingFlags.SAMPLED);
    assertThat(toSamplingFlags(SamplingFlags.DEBUG.flags))
      .isSameAs(SamplingFlags.DEBUG);
    assertThat(toSamplingFlags(SamplingFlags.EMPTY.flags | FLAG_SAMPLED_LOCAL))
      .isSameAs(SamplingFlags.EMPTY_SAMPLED_LOCAL);
    assertThat(toSamplingFlags(SamplingFlags.NOT_SAMPLED.flags | FLAG_SAMPLED_LOCAL))
      .isSameAs(SamplingFlags.NOT_SAMPLED_SAMPLED_LOCAL);
    assertThat(toSamplingFlags(SamplingFlags.SAMPLED.flags | FLAG_SAMPLED_LOCAL))
      .isSameAs(SamplingFlags.SAMPLED_SAMPLED_LOCAL);
    assertThat(toSamplingFlags(SamplingFlags.DEBUG.flags | FLAG_SAMPLED_LOCAL))
      .isSameAs(SamplingFlags.DEBUG_SAMPLED_LOCAL);
  }