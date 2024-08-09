    @Override
    protected void assertHooksOnFailure(Func0<TestHystrixCommand<Integer>> ctor, Action1<TestHystrixCommand<Integer>> assertion) {
        assertExecute(ctor.call(), assertion, false);
        assertBlockingQueue(ctor.call(), assertion, false);
        assertNonBlockingQueue(ctor.call(), assertion, false, false);
        assertBlockingObserve(ctor.call(), assertion, false);
        assertNonBlockingObserve(ctor.call(), assertion, false);
    }