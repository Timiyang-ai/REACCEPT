    @Override
    protected void assertHooksOnSuccess(Func0<TestHystrixCommand<Integer>> ctor, Action1<TestHystrixCommand<Integer>> assertion) {
        assertExecute(ctor.call(), assertion, true);
        assertBlockingQueue(ctor.call(), assertion, true);
        assertNonBlockingQueue(ctor.call(), assertion, true, false);
        assertBlockingObserve(ctor.call(), assertion, true);
        assertNonBlockingObserve(ctor.call(), assertion, true);
    }