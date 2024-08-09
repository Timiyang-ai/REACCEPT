    @Test
    public void setValue_oneOfTwoLifecyclesAreCreatedNoEvent() {

        // Arrange.

        mLiveData.observe(mOwner3, mObserver3);
        mLiveData.observe(mOwner4, mObserver4);

        GenericLifecycleObserver lifecycleObserver3 = getGenericLifecycleObserver(mLifecycle3);
        GenericLifecycleObserver lifecycleObserver4 = getGenericLifecycleObserver(mLifecycle4);

        when(mLifecycle3.getCurrentState()).thenReturn(Lifecycle.State.STARTED);
        when(mLifecycle4.getCurrentState()).thenReturn(Lifecycle.State.STARTED);
        lifecycleObserver3.onStateChanged(mOwner3, Lifecycle.Event.ON_START);
        lifecycleObserver4.onStateChanged(mOwner4, Lifecycle.Event.ON_START);

        when(mLifecycle3.getCurrentState()).thenReturn(Lifecycle.State.CREATED);

        reset(mActiveObserversChanged);
        reset(mObserver3);
        reset(mObserver4);

        // Act.

        mLiveData.setValue("1");

        // Assert.

        verify(mActiveObserversChanged, never()).onCall(anyBoolean());
        verify(mObserver3, never()).onChanged(anyString());
        verify(mObserver4).onChanged("1");
    }