  @Test public void normalizeTraceId_truncates64BitZeroPrefix() {
    assertThat(normalizeTraceId("0000000000000000000000000000162e"))
      .isEqualTo("000000000000162e");
  }