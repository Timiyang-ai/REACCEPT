@Test
    public void testObserveOn() {
        Subscriber<Integer> observer = TestHelper.mockSubscriber();
        Flowable.just(1, 2, 3).observeOn(ImmediateThinScheduler.INSTANCE).subscribe(observer);

        verify(observer, times(1)).onNext(1);
        verify(observer, times(1)).onNext(2);
        verify(observer, times(1)).onNext(3);
        verify(observer, times(1)).onComplete();
    }