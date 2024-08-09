    @Test
    public void decorateRunnable() {
        Runnable runnable = mock(Runnable.class);
        Runnable decorated = RateLimiter.decorateRunnable(limit, runnable);
        given(limit.acquirePermission(1)).willReturn(false);
        Try decoratedRunnableResult = Try.success(decorated).andThen(Runnable::run);
        assertThat(decoratedRunnableResult.isFailure()).isTrue();
        assertThat(decoratedRunnableResult.getCause()).isInstanceOf(RequestNotPermitted.class);
        then(runnable).should(never()).run();
        given(limit.acquirePermission(1)).willReturn(true);

        Try secondRunnableResult = Try.success(decorated).andThen(Runnable::run);

        assertThat(secondRunnableResult.isSuccess()).isTrue();
        then(runnable).should().run();
    }