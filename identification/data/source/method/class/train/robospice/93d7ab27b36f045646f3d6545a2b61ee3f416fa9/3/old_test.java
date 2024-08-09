    public void test_cancel_cancels_1_request() throws InterruptedException {
        // given
        SpiceRequestStub<String> spiceRequestStub = new SpiceRequestSucceedingStub<String>(String.class, TEST_RETURNED_DATA);
        spiceManager.start(getContext());
        // when
        spiceManager.cancel(spiceRequestStub);
        Thread.sleep(WAIT_BEFORE_EXECUTING_REQUEST_LARGE);

        // test
        assertTrue(spiceRequestStub.isCancelled());
    }