  @Test
  public void getCurrentSpan_WithSpan() {
    assertThat(noopTracer.getCurrentSpan()).isSameInstanceAs(BlankSpan.INSTANCE);
    Scope ws = noopTracer.withSpan(span);
    try {
      assertThat(noopTracer.getCurrentSpan()).isSameInstanceAs(span);
    } finally {
      ws.close();
    }
    assertThat(noopTracer.getCurrentSpan()).isSameInstanceAs(BlankSpan.INSTANCE);
  }