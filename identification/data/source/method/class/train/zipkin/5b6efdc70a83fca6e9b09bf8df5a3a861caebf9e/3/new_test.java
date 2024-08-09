  @Test public void shared_canUnset() {
    assertThat(base.toBuilder().shared(true).shared(null).build().shared())
      .isNull();
  }