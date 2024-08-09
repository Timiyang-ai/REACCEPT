  @Test(dataProvider = "successful")
  public void isReady_success(CompletableFuture<?> future) {
    assertThat(Async.isReady(future), is(true));
  }