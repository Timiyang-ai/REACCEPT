  @Test public void debug_canUnset() {
    assertThat(base.toBuilder().debug(true).debug(null).build().debug())
      .isNull();
  }