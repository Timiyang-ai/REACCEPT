    public void test_cancelAllRequests_cancels_2_requests() throws InterruptedException {
        // given
        spiceManager.start(getContext());
        SpiceRequestStub<String> spiceRequestStub = new SpiceRequestFailingStub<String>(TEST_CLASS, WAIT_BEFORE_EXECUTING_REQUEST_LARGE);
        SpiceRequestStub<String> spiceRequestStub2 = new SpiceRequestFailingStub<String>(TEST_CLASS, WAIT_BEFORE_EXECUTING_REQUEST_LARGE);
        RequestListenerWithProgressStub<String> requestListenerStub = new RequestListenerWithProgressStub<String>();
        RequestListenerWithProgressStub<String> requestListenerStub2 = new RequestListenerWithProgressStub<String>();

        // when
        spiceManager.execute(spiceRequestStub, TEST_CACHE_KEY, TEST_DURATION, requestListenerStub);
        spiceManager.execute(spiceRequestStub2, TEST_CACHE_KEY2, TEST_DURATION, requestListenerStub2);
        spiceManager.cancelAllRequests();

        requestListenerStub.awaitComplete(REQUEST_COMPLETION_TIME_OUT);
        requestListenerStub2.awaitComplete(REQUEST_COMPLETION_TIME_OUT);

        requestListenerStub.await(REQUEST_COMPLETION_TIME_OUT);
        requestListenerStub2.await(REQUEST_COMPLETION_TIME_OUT);

        // spiceRequestStub.awaitForLoadDataFromNetworkIsCalled(REQUEST_COMPLETION_TIME_OUT);
        // spiceRequestStub2.awaitForLoadDataFromNetworkIsCalled(REQUEST_COMPLETION_TIME_OUT);

        // test
        assertTrue(spiceRequestStub.isCancelled());
        assertTrue(spiceRequestStub2.isCancelled());
        // can we guarantee that ? If cancel is too fast, spiceManager won't
        // have passed the request to spice service.
        assertTrue(requestListenerStub.isComplete());
        assertTrue(requestListenerStub2.isComplete());
        assertFalse(requestListenerStub.isSuccessful());
        assertFalse(requestListenerStub2.isSuccessful());
        System.out.println(requestListenerStub.getReceivedException());
        System.out.println(requestListenerStub2.getReceivedException());
        assertTrue(requestListenerStub.getReceivedException() instanceof RequestCancelledException);
        assertTrue(requestListenerStub2.getReceivedException() instanceof RequestCancelledException);
    }