  @Test
  public void test_isShort() {
    assertThat(LongShort.LONG.isShort()).isFalse();
    assertThat(LongShort.SHORT.isShort()).isTrue();
  }