    public void test_dontNotifyRequestListenersForRequest_with_2_request_and_one_not_notified() throws InterruptedException, CacheLoadingException, CacheSavingException, CacheCreationException {
        // given
        CachedSpiceRequestStub<String> stubRequest = createSuccessfulRequest(TEST_CLASS, TEST_CACHE_KEY, TEST_DURATION, TEST_RETURNED_DATA, WAIT_BEFORE_REQUEST_EXECUTION);
        CachedSpiceRequestStub<String> stubRequest2 = createSuccessfulRequest(TEST_CLASS, TEST_CACHE_KEY2, TEST_DURATION, TEST_RETURNED_DATA);
        RequestListenerWithProgressStub<String> requestListenerStub = new RequestListenerWithProgressStub<String>();
        RequestListenerWithProgressStub<String> requestListenerStub2 = new RequestListenerWithProgressStub<String>();
        Set<RequestListener<?>> requestListenerSet = new HashSet<RequestListener<?>>();
        requestListenerSet.add(requestListenerStub);
        Set<RequestListener<?>> requestListenerSet2 = new HashSet<RequestListener<?>>();
        requestListenerSet2.add(requestListenerStub2);

        EasyMock.expect(mockCacheManager.loadDataFromCache(EasyMock.eq(TEST_CLASS), EasyMock.eq(TEST_CACHE_KEY), EasyMock.eq(TEST_DURATION))).andReturn(null);
        EasyMock.expectLastCall().anyTimes();
        EasyMock.expect(mockCacheManager.saveDataToCacheAndReturnData(EasyMock.eq(TEST_RETURNED_DATA), EasyMock.eq(TEST_CACHE_KEY))).andReturn(TEST_RETURNED_DATA);
        EasyMock.expectLastCall().anyTimes();
        EasyMock.expect(mockCacheManager.loadDataFromCache(EasyMock.eq(TEST_CLASS), EasyMock.eq(TEST_CACHE_KEY2), EasyMock.eq(TEST_DURATION))).andReturn(null);
        EasyMock.expect(mockCacheManager.saveDataToCacheAndReturnData(EasyMock.eq(TEST_RETURNED_DATA), EasyMock.eq(TEST_CACHE_KEY2))).andReturn(TEST_RETURNED_DATA);
        EasyMock.replay(mockCacheManager);

        // when
        requestProcessorUnderTest.addRequest(stubRequest, requestListenerSet);
        requestProcessorUnderTest.dontNotifyRequestListenersForRequest(stubRequest, requestListenerSet);
        requestProcessorUnderTest.addRequest(stubRequest2, requestListenerSet2);

        stubRequest.await(REQUEST_COMPLETION_TIME_OUT);
        requestListenerStub2.await(REQUEST_COMPLETION_TIME_OUT);

        // test
        EasyMock.verify(mockCacheManager);
        // TODO check this !! assertTrue(
        // stubRequest.isLoadDataFromNetworkCalled() );
        assertTrue(stubRequest2.isLoadDataFromNetworkCalled());
        assertNull(requestListenerStub.isSuccessful());
        assertFalse(requestListenerStub.isComplete());
        assertTrue(requestListenerStub2.isSuccessful());
        assertTrue(requestListenerStub2.isComplete());
    }