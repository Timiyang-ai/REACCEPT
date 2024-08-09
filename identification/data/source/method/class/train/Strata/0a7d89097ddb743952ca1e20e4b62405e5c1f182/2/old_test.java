  @Test
  public void test_isLong() {
    assertThat(LongShort.LONG.isLong()).isTrue();
    assertThat(LongShort.SHORT.isLong()).isFalse();
  }