    @Test
    public void decorateCheckedRunnable() throws Throwable {
        CheckedRunnable runnable = mock(CheckedRunnable.class);
        CheckedRunnable decorated = RateLimiter.decorateCheckedRunnable(limit, runnable);
        given(limit.acquirePermission(1)).willReturn(false);
        Try decoratedRunnableResult = Try.run(decorated);
        assertThat(decoratedRunnableResult.isFailure()).isTrue();
        assertThat(decoratedRunnableResult.getCause()).isInstanceOf(RequestNotPermitted.class);
        then(runnable).should(never()).run();
        given(limit.acquirePermission(1)).willReturn(true);

        Try secondRunnableResult = Try.run(decorated);

        assertThat(secondRunnableResult.isSuccess()).isTrue();
        then(runnable).should().run();
    }