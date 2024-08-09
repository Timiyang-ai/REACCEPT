  @Test
  public void test_readBucketTimestamps_noCommitLogs() {
    assertThat(strategy.readBucketTimestamps())
        .containsExactly(1, START_OF_TIME, 2, START_OF_TIME, 3, START_OF_TIME);
  }