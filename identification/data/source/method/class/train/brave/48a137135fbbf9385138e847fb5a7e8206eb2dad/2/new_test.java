  @Test public void sampled_flags() {
    assertThat(SamplingFlags.debug(false, SamplingFlags.DEBUG.flags))
      .isEqualTo(SamplingFlags.SAMPLED.flags)
      .isEqualTo(FLAG_SAMPLED_SET | FLAG_SAMPLED);
  }