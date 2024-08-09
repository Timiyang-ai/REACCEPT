  @Test
  public void isValid() {
    assertThat(SpanId.INVALID.isValid()).isFalse();
    assertThat(first.isValid()).isTrue();
    assertThat(second.isValid()).isTrue();
  }