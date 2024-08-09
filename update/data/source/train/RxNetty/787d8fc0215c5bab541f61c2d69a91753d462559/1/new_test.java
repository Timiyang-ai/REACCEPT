@Test
        public void testError1() {
            // we are using synchronous execution to test this exactly rather than non-deterministic concurrent behavior
            final Observable<String> o1 = new TestErrorObservable("four", null, "six"); // we expect to lose "six"
            final Observable<String> o2 = new TestErrorObservable("one", "two", "three"); // we expect to lose all of these since o1 is done first and fails

            @SuppressWarnings("unchecked")
            Observable<String> m = Observable.create(merge(o1, o2));
            m.subscribe(stringObserver);

            verify(stringObserver, times(1)).onError(any(NullPointerException.class));
            verify(stringObserver, never()).onCompleted();
            verify(stringObserver, times(0)).onNext("one");
            verify(stringObserver, times(0)).onNext("two");
            verify(stringObserver, times(0)).onNext("three");
            verify(stringObserver, times(1)).onNext("four");
            verify(stringObserver, times(0)).onNext("five");
            verify(stringObserver, times(0)).onNext("six");
        }