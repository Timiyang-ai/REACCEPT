  @Test
  public void toLowerBase16() {
    assertThat(SpanId.INVALID.toLowerBase16()).isEqualTo("0000000000000000");
    assertThat(first.toLowerBase16()).isEqualTo("0000000000000061");
    assertThat(second.toLowerBase16()).isEqualTo("ff00000000000041");
  }