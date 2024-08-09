  @Test
  public void toLowerBase16() {
    assertThat(TraceId.INVALID.toLowerBase16()).isEqualTo("00000000000000000000000000000000");
    assertThat(first.toLowerBase16()).isEqualTo("00000000000000000000000000000061");
    assertThat(second.toLowerBase16()).isEqualTo("ff000000000000000000000000000041");
  }