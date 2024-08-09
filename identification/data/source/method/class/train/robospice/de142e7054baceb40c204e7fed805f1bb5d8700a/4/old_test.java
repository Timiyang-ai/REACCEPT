    public void test_addListenerIfPending_receives_no_events_except_request_not_found_when_there_is_no_request_pending() throws InterruptedException {
        // given
        spiceManager.start(getContext());
        SpiceRequestStub<String> spiceRequestStub = new SpiceRequestFailingStub<String>(TEST_CLASS);
        PendingRequestListenerWithProgressStub<String> requestListenerStub = new PendingRequestListenerWithProgressStub<String>();

        // when
        spiceManager.addListenerIfPending(TEST_CLASS, TEST_CACHE_KEY, requestListenerStub);

        spiceRequestStub.awaitForLoadDataFromNetworkIsCalled(WAIT_BEFORE_EXECUTING_REQUEST_LARGE);

        // test
        assertNull(requestListenerStub.isSuccessful());
        assertFalse(requestListenerStub.isComplete());
        assertNull(requestListenerStub.getReceivedException());
        assertTrue(requestListenerStub.isRequestNotFound());
    }