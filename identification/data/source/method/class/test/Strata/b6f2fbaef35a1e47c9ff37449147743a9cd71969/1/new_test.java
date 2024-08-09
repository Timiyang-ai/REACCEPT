  @Test
  public void ofNullable_nonNull() {
    Result<Integer> test = Result.ofNullable(6);
    assertThat(test.isFailure()).isFalse();
    assertThat(test.getValue().intValue()).isEqualTo(6);
  }