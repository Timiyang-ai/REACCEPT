    public void test_addRequest_with_2_requests_and_one_is_cancelled() throws InterruptedException, CacheLoadingException, CacheSavingException, CacheCreationException {
        // given
        CachedSpiceRequestStub<String> stubRequest = createSuccessfulRequest(TEST_CLASS, TEST_CACHE_KEY, TEST_DURATION, TEST_RETURNED_DATA);
        CachedSpiceRequestStub<String> stubRequest2 = createSuccessfulRequest(TEST_CLASS, TEST_CACHE_KEY2, TEST_DURATION, TEST_RETURNED_DATA);
        RequestListenerWithProgressStub<String> requestListenerStub = new RequestListenerWithProgressStub<String>();
        RequestListenerWithProgressStub<String> requestListenerStub2 = new RequestListenerWithProgressStub<String>();
        Set<RequestListener<?>> requestListenerSet = new HashSet<RequestListener<?>>();
        requestListenerSet.add(requestListenerStub);
        Set<RequestListener<?>> requestListenerSet2 = new HashSet<RequestListener<?>>();
        requestListenerSet2.add(requestListenerStub2);

        EasyMock.expect(mockCacheManager.loadDataFromCache(EasyMock.eq(TEST_CLASS), EasyMock.eq(TEST_CACHE_KEY2), EasyMock.eq(TEST_DURATION))).andReturn(TEST_RETURNED_DATA);
        EasyMock.replay(mockCacheManager);

        stubRequest.cancel();

        // when
        requestProcessorUnderTest.addRequest(stubRequest, requestListenerSet);
        requestProcessorUnderTest.addRequest(stubRequest2, requestListenerSet2);
        requestListenerStub.await(REQUEST_COMPLETION_TIME_OUT);
        requestListenerStub2.await(REQUEST_COMPLETION_TIME_OUT);

        // test
        EasyMock.verify(mockCacheManager);
        assertFalse(stubRequest.isLoadDataFromNetworkCalled());

        assertNotNull(requestListenerStub.isSuccessful());
        assertFalse(requestListenerStub.isSuccessful());
        assertTrue(requestListenerStub.getReceivedException() instanceof RequestCancelledException);
        assertTrue(requestListenerStub.isComplete());

        assertNotNull(requestListenerStub2.isSuccessful());
        assertTrue(requestListenerStub2.isSuccessful());
        assertTrue(requestListenerStub2.isComplete());
    }