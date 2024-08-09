@Test
    public void testComplete() {
        MetadataCheckpointOperation op = new MetadataCheckpointOperation();

        AtomicLong callbackSeqNo = new AtomicLong(DEFAULT_SEQ_NO);
        AtomicBoolean failureCallbackCalled = new AtomicBoolean();
        CompletableOperation co = new CompletableOperation(op, callbackSeqNo::set, ex -> failureCallbackCalled.set(true));

        AssertExtensions.assertThrows("complete() succeeded even if Operation had no Sequence Number.",
                co::complete,
                ex -> ex instanceof IllegalStateException);

        Assert.assertEquals("Success callback was invoked for illegal complete() call.", DEFAULT_SEQ_NO, callbackSeqNo.get());
        Assert.assertFalse("Failure callback was invoked for illegal complete() call.", failureCallbackCalled.get());

        op.setSequenceNumber(VALID_SEQ_NO);
        co.complete();
        Assert.assertEquals("Success callback not invoked with the correct argument after valid complete() call.", VALID_SEQ_NO, callbackSeqNo.get());
        Assert.assertFalse("Failure callback was invoked for valid complete() call.", failureCallbackCalled.get());
    }