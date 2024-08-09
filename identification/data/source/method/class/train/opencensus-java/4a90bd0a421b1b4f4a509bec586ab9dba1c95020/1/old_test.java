  @Test
  public void withSpan_CloseDetaches() {
    assertThat(CurrentSpanUtils.getCurrentSpan()).isEqualTo(BlankSpan.INSTANCE);
    Scope ws = CurrentSpanUtils.withSpan(span, false);
    try {
      assertThat(CurrentSpanUtils.getCurrentSpan()).isSameInstanceAs(span);
    } finally {
      ws.close();
    }
    assertThat(CurrentSpanUtils.getCurrentSpan()).isEqualTo(BlankSpan.INSTANCE);
    verifyZeroInteractions(span);
  }