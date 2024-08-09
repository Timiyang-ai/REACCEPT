    public void test_shouldStop_stops_requests_immediatly() throws InterruptedException {
        // given
        spiceManager.start(getContext());
        SpiceRequestStub<String> spiceRequestStub = new SpiceRequestFailingStub<String>(TEST_CLASS, WAIT_BEFORE_EXECUTING_REQUEST_LARGE);
        SpiceRequestStub<String> spiceRequestStub2 = new SpiceRequestFailingStub<String>(TEST_CLASS, WAIT_BEFORE_EXECUTING_REQUEST_LARGE);
        RequestListenerStub<String> requestListenerStub = new RequestListenerStub<String>();
        RequestListenerStub<String> requestListenerStub2 = new RequestListenerStub<String>();

        // when
        spiceManager.execute(spiceRequestStub, TEST_CACHE_KEY, TEST_DURATION, requestListenerStub);
        spiceManager.execute(spiceRequestStub2, TEST_CACHE_KEY2, TEST_DURATION, requestListenerStub2);
        spiceManager.shouldStop();

        spiceRequestStub.awaitForLoadDataFromNetworkIsCalled(WAIT_BEFORE_EXECUTING_REQUEST_LARGE);
        spiceRequestStub2.awaitForLoadDataFromNetworkIsCalled(WAIT_BEFORE_EXECUTING_REQUEST_LARGE);

        // test
        // no guarantee on that
        // assertTrue( spiceRequestStub.isLoadDataFromNetworkCalled() );
        // assertTrue( spiceRequestStub2.isLoadDataFromNetworkCalled() );
        assertNull(requestListenerStub.isSuccessful());
        assertNull(requestListenerStub2.isSuccessful());
    }