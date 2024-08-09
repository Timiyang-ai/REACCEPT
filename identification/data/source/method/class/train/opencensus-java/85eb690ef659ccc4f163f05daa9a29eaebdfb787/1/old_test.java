  @Test
  public void isValid() {
    assertThat(TraceId.INVALID.isValid()).isFalse();
    assertThat(first.isValid()).isTrue();
    assertThat(second.isValid()).isTrue();
  }