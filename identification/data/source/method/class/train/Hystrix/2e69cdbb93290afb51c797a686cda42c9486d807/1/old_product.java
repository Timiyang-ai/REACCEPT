private <T> void assertHooksOnFailure(Func0<TestHystrixCommand<T>> ctor, Action1<TestHystrixCommand<T>> assertion) {
            assertExecute(ctor.call(), assertion, false);
            assertBlockingQueue(ctor.call(), assertion, false);
            assertNonBlockingQueue(ctor.call(), assertion, false);
            assertBlockingObserve(ctor.call(), assertion, false);
            assertNonBlockingObserve(ctor.call(), assertion, false);
        }