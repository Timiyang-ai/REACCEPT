  @Test
  public void fromLowerBase16() {
    assertThat(SpanId.fromLowerBase16("0000000000000000")).isEqualTo(SpanId.INVALID);
    assertThat(SpanId.fromLowerBase16("0000000000000061")).isEqualTo(first);
    assertThat(SpanId.fromLowerBase16("ff00000000000041")).isEqualTo(second);
  }