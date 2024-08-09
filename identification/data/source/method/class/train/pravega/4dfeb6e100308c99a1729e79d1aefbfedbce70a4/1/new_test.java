@Test
    public void testComplete() {
        MetadataCheckpointOperation op = new MetadataCheckpointOperation();

        AtomicBoolean callback = new AtomicBoolean(false);
        AtomicBoolean failureCallbackCalled = new AtomicBoolean();
        CompletableOperation co = new CompletableOperation(op, v -> callback.set(true), ex -> failureCallbackCalled.set(true));

        AssertExtensions.assertThrows("complete() succeeded even if Operation had no Sequence Number.",
                co::complete,
                ex -> ex instanceof IllegalStateException);

        Assert.assertFalse("Success callback was invoked for illegal complete() call.", callback.get());
        Assert.assertFalse("Failure callback was invoked for illegal complete() call.", failureCallbackCalled.get());

        op.setSequenceNumber(VALID_SEQ_NO);
        co.complete();
        Assert.assertTrue("Success callback not invoked after valid complete() call.", callback.get());
        Assert.assertFalse("Failure callback was invoked for valid complete() call.", failureCallbackCalled.get());
    }