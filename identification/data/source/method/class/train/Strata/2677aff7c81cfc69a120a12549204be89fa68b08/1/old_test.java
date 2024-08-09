  @Test
  public void test_summaryDescription() {
    assertThat(sut().summaryDescription()).isEqualTo("Jun17");
    assertThat(sut2().summaryDescription()).isEqualTo("W2Sep17");
  }