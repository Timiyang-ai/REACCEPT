@Test
    public void testObserveOn() {
        Subscriber<Integer> subscriber = TestHelper.mockSubscriber();
        Flowable.just(1, 2, 3).observeOn(ImmediateThinScheduler.INSTANCE).subscribe(subscriber);

        verify(subscriber, times(1)).onNext(1);
        verify(subscriber, times(1)).onNext(2);
        verify(subscriber, times(1)).onNext(3);
        verify(subscriber, times(1)).onComplete();
    }