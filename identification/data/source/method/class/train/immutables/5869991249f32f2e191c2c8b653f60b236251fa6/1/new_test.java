  @Test
  public void transform() {
    FluentFuture<String> future =
        FluentFutures.from(Futures.immediateFuture(1))
            .transform(this);

    check(calledTimes.get()).is(1);
    check(future.getUnchecked()).is("1");
  }