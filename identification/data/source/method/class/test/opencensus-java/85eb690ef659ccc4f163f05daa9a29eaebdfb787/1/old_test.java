  @Test
  public void getSpanId() {
    assertThat(first.getSpanId()).isEqualTo(SpanId.fromBytes(firstSpanIdBytes));
    assertThat(second.getSpanId()).isEqualTo(SpanId.fromBytes(secondSpanIdBytes));
  }