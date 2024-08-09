  @Test
  public void fromLowerBase16() {
    assertThat(TraceId.fromLowerBase16("00000000000000000000000000000000"))
        .isEqualTo(TraceId.INVALID);
    assertThat(TraceId.fromLowerBase16("00000000000000000000000000000061")).isEqualTo(first);
    assertThat(TraceId.fromLowerBase16("ff000000000000000000000000000041")).isEqualTo(second);
  }