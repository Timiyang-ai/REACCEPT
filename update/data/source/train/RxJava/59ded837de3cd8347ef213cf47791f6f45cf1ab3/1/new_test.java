@Deprecated
    @Test
    public void testFromFunc0() {
        Func0<Integer> func = new Func0<Integer>() {
            @Override
            public Integer call() {
                return 1;
            }
        };
        
        Observable<Integer> source = Async.fromFunc0(func, scheduler);
        
        for (int i = 0; i < 3; i++) {
            
            Observer<Object> observer = mock(Observer.class);
            source.subscribe(new TestObserver<Object>(observer));

            InOrder inOrder = inOrder(observer);

            inOrder.verify(observer, never()).onNext(any());
            inOrder.verify(observer, never()).onCompleted();

            scheduler.advanceTimeBy(1, TimeUnit.MILLISECONDS);

            inOrder.verify(observer, times(1)).onNext(1);
            inOrder.verify(observer, times(1)).onCompleted();
            inOrder.verifyNoMoreInteractions();
            verify(observer, never()).onError(any(Throwable.class));
        }
    }