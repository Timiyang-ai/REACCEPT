  @Test
  public void flatCombine_iterableWithFailures() {
    Result<String> success1 = Result.success("success 1");
    Result<String> success2 = Result.success("success 2");
    Result<String> failure1 = Result.failure(MISSING_DATA, "failure 1");
    Result<String> failure2 = Result.failure(ERROR, "failure 2");
    Set<Result<String>> results = ImmutableSet.of(success1, success2, failure1, failure2);

    assertThat(Result.flatCombine(results, Result::success))
        .isFailure(FailureReason.MULTIPLE);
  }