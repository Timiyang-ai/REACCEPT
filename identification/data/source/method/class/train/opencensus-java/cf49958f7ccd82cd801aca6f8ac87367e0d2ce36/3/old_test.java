  @Test
  public void getCurrentSpan_WhenNoContext() {
    assertThat(CurrentSpanUtils.getCurrentSpan()).isEqualTo(BlankSpan.INSTANCE);
  }