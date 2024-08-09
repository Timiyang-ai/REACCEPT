  @Test public void next() {
    assertThat(threadLocalSpan.next())
      .isEqualTo(threadLocalSpan.remove());
  }