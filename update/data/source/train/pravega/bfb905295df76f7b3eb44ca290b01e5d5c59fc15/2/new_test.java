@Test
    public void testProcess() throws Exception {
        int segmentCount = 10;
        int operationCountPerType = 5;

        // Add to MTL + Add to ReadIndex (append; beginMerge).
        SequencedItemList<Operation> opLog = new SequencedItemList<>();
        ArrayList<TestReadIndex.MethodInvocation> methodInvocations = new ArrayList<>();
        TestReadIndex readIndex = new TestReadIndex(methodInvocations::add);
        AtomicInteger flushCallbackCallCount = new AtomicInteger();
        MemoryStateUpdater updater = new MemoryStateUpdater(opLog, readIndex, flushCallbackCallCount::incrementAndGet);
        ArrayList<Operation> operations = populate(updater, segmentCount, operationCountPerType);

        // Verify they were properly processed.
        int triggerFutureCount = (int) methodInvocations.stream().filter(mi -> mi.methodName.equals(TestReadIndex.TRIGGER_FUTURE_READS)).count();
        int addCount = methodInvocations.size() - triggerFutureCount;
        Assert.assertEquals("Unexpected number of items added to ReadIndex.",
                operations.size() - segmentCount * operationCountPerType, addCount);
        Assert.assertEquals("Unexpected number of calls to the ReadIndex triggerFutureReads method.", 1, triggerFutureCount);
        Assert.assertEquals("Unexpected number of calls to the flushCallback provided in the constructor.", 1, flushCallbackCallCount.get());

        // Verify add calls.
        Iterator<Operation> logIterator = opLog.read(-1, operations.size());
        int currentIndex = -1;
        int currentReadIndex = -1;
        while (logIterator.hasNext()) {
            currentIndex++;
            Operation expected = operations.get(currentIndex);
            Operation actual = logIterator.next();
            if (expected instanceof StorageOperation) {
                currentReadIndex++;
                TestReadIndex.MethodInvocation invokedMethod = methodInvocations.get(currentReadIndex);
                if (expected instanceof StreamSegmentAppendOperation) {
                    Assert.assertTrue("StreamSegmentAppendOperation was not added as a CachedStreamSegmentAppendOperation to the Memory Log.", actual instanceof CachedStreamSegmentAppendOperation);
                    StreamSegmentAppendOperation appendOp = (StreamSegmentAppendOperation) expected;
                    Assert.assertEquals("Append with SeqNo " + expected.getSequenceNumber() + " was not added to the ReadIndex.", TestReadIndex.APPEND, invokedMethod.methodName);
                    Assert.assertEquals("Append with SeqNo " + expected.getSequenceNumber() + " was added to the ReadIndex with wrong arguments.", appendOp.getStreamSegmentId(), invokedMethod.args.get("streamSegmentId"));
                    Assert.assertEquals("Append with SeqNo " + expected.getSequenceNumber() + " was added to the ReadIndex with wrong arguments.", appendOp.getStreamSegmentOffset(), invokedMethod.args.get("offset"));
                    Assert.assertEquals("Append with SeqNo " + expected.getSequenceNumber() + " was added to the ReadIndex with wrong arguments.", appendOp.getData(), invokedMethod.args.get("data"));
                } else if (expected instanceof MergeTransactionOperation) {
                    MergeTransactionOperation mergeOp = (MergeTransactionOperation) expected;
                    Assert.assertEquals("Merge with SeqNo " + expected.getSequenceNumber() + " was not added to the ReadIndex.", TestReadIndex.BEGIN_MERGE, invokedMethod.methodName);
                    Assert.assertEquals("Merge with SeqNo " + expected.getSequenceNumber() + " was added to the ReadIndex with wrong arguments.", mergeOp.getStreamSegmentId(), invokedMethod.args.get("targetStreamSegmentId"));
                    Assert.assertEquals("Merge with SeqNo " + expected.getSequenceNumber() + " was added to the ReadIndex with wrong arguments.", mergeOp.getStreamSegmentOffset(), invokedMethod.args.get("offset"));
                    Assert.assertEquals("Merge with SeqNo " + expected.getSequenceNumber() + " was added to the ReadIndex with wrong arguments.", mergeOp.getTransactionSegmentId(), invokedMethod.args.get("sourceStreamSegmentId"));
                }
            }
        }

        // Verify triggerFutureReads args.
        @SuppressWarnings("unchecked")
        Collection<Long> triggerSegmentIds = (Collection<Long>) methodInvocations
                .stream()
                .filter(mi -> mi.methodName.equals(TestReadIndex.TRIGGER_FUTURE_READS))
                .findFirst().get()
                .args.get("streamSegmentIds");
        val expectedSegmentIds = operations.stream()
                .filter(op -> op instanceof SegmentOperation)
                .map(op -> ((SegmentOperation) op).getStreamSegmentId())
                .collect(Collectors.toSet());

        AssertExtensions.assertContainsSameElements("ReadIndex.triggerFutureReads() was called with the wrong set of StreamSegmentIds.", expectedSegmentIds, triggerSegmentIds);

        // Test DataCorruptionException.
        AssertExtensions.assertThrows(
                "MemoryStateUpdater accepted an operation that was out of order.",
                () -> updater.process(new MergeTransactionOperation(1, 2)), // This does not have a SequenceNumber set, so it should trigger a DCE.
                ex -> ex instanceof DataCorruptionException);
    }