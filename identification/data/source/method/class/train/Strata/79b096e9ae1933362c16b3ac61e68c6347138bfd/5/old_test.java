  @Test
  public void success() {
    Result<String> test = Result.success("success");
    assertThat(test.isSuccess()).isEqualTo(true);
    assertThat(test.isFailure()).isEqualTo(false);
    assertThat(test.get()).hasValue("success");
    assertThat(test.getValue()).isEqualTo("success");
    assertThat(test.getValueOrElse("blue")).isEqualTo("success");
    assertThat(test.getValueOrElse(null)).isEqualTo("success");
    assertThatIllegalArgumentException().isThrownBy(() -> test.getValueOrElseApply(null));
  }