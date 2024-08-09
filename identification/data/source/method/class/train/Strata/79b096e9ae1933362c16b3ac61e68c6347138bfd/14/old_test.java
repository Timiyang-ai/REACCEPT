  @Test
  public void failure() {
    IllegalArgumentException ex = new IllegalArgumentException("failure");
    Result<String> test = Result.failure(ex);
    assertThat(test.isSuccess()).isEqualTo(false);
    assertThat(test.isFailure()).isEqualTo(true);
    assertThat(test.get()).isEmpty();
    assertThat(test.getValueOrElse("blue")).isEqualTo("blue");
    assertThat(test.getValueOrElseApply(f -> "blue")).isEqualTo("blue");
    assertThat(test.getValueOrElseApply(Failure::getMessage)).isEqualTo("failure");
    assertThat(test.getValueOrElse(null)).isNull();
    assertThatIllegalArgumentException().isThrownBy(() -> test.getValueOrElseApply(null));
    assertThat(test.getFailure().getReason()).isEqualTo(ERROR);
    assertThat(test.getFailure().getMessage()).isEqualTo("failure");
    assertThat(test.getFailure().getItems().size()).isEqualTo(1);
    FailureItem item = test.getFailure().getFirstItem();
    assertThat(item.getReason()).isEqualTo(ERROR);
    assertThat(item.getMessage()).isEqualTo("failure");
    assertThat(item.getCauseType().get()).isEqualTo(ex.getClass());
    assertThat(item.getStackTrace()).isEqualTo(Throwables.getStackTraceAsString(ex).replace(System.lineSeparator(), "\n"));
  }