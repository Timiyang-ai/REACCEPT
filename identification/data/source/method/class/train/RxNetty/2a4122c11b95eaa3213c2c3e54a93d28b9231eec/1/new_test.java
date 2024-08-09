@Test
        public void testError2() {
            // we are using synchronous execution to test this exactly rather than non-deterministic concurrent behavior
            final Observable<String> o1 = new TestErrorObservable("one", "two", "three");
            final Observable<String> o2 = new TestErrorObservable("four", null, "six"); // we expect to lose "six"
            final Observable<String> o3 = new TestErrorObservable("seven", "eight", null);// we expect to lose all of these since o2 is done first and fails
            final Observable<String> o4 = new TestErrorObservable("nine");// we expect to lose all of these since o2 is done first and fails

            @SuppressWarnings("unchecked")
            Observable<String> m = merge(o1, o2, o3, o4);
            m.subscribe(stringObserver);

            verify(stringObserver, times(1)).onError(any(NullPointerException.class));
            verify(stringObserver, never()).onCompleted();
            verify(stringObserver, times(1)).onNext("one");
            verify(stringObserver, times(1)).onNext("two");
            verify(stringObserver, times(1)).onNext("three");
            verify(stringObserver, times(1)).onNext("four");
            verify(stringObserver, times(0)).onNext("five");
            verify(stringObserver, times(0)).onNext("six");
            verify(stringObserver, times(0)).onNext("seven");
            verify(stringObserver, times(0)).onNext("eight");
            verify(stringObserver, times(0)).onNext("nine");
        }