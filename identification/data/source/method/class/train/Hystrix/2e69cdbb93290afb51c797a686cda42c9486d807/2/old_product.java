private <T> void assertHooksOnSuccess(Func0<TestHystrixCommand<T>> ctor, Action1<TestHystrixCommand<T>> assertion) {
            assertExecute(ctor.call(), assertion, true);
            assertBlockingQueue(ctor.call(), assertion, true);
            assertNonBlockingQueue(ctor.call(), assertion, true);
            assertBlockingObserve(ctor.call(), assertion, true);
            assertNonBlockingObserve(ctor.call(), assertion, true);
        }