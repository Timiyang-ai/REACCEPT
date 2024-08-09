@Test
    public void testAdd() throws Exception {
        int segmentCount = 10;
        int operationCountPerType = 5;

        // Add to MTL + Add to ReadIndex (append; beginMerge).
        MemoryOperationLog opLog = new MemoryOperationLog();
        ArrayList<TestReadIndex.MethodInvocation> methodInvocations = new ArrayList<>();
        TestReadIndex readIndex = new TestReadIndex(methodInvocations::add);
        MemoryLogUpdater updater = new MemoryLogUpdater(opLog, new CacheUpdater(new InMemoryCache("0"), readIndex));
        ArrayList<Operation> operations = populate(updater, segmentCount, operationCountPerType);

        // Verify they were properly processed.
        Assert.assertEquals("Unexpected size for MemoryOperationLog.", operations.size(), opLog.getSize());
        Assert.assertEquals("Unexpected number of items added to ReadIndex.", operations.size() - segmentCount * operationCountPerType, methodInvocations.size());

        Iterator<Operation> logIterator = opLog.read(op -> true, opLog.getSize());
        int currentIndex = -1;
        int currentReadIndex = -1;
        while (logIterator.hasNext()) {
            currentIndex++;
            Operation expected = operations.get(currentIndex);
            Assert.assertEquals("Unexpected operation queued to MemoryOperationLog at sequence " + currentIndex, expected, logIterator.next());
            if (expected instanceof StorageOperation) {
                currentReadIndex++;
                TestReadIndex.MethodInvocation invokedMethod = methodInvocations.get(currentReadIndex);
                if (expected instanceof StreamSegmentAppendOperation) {
                    StreamSegmentAppendOperation appendOp = (StreamSegmentAppendOperation) expected;
                    CacheKey expectedKey = new CacheKey(appendOp.getStreamSegmentId(), appendOp.getStreamSegmentOffset());
                    Assert.assertEquals("Append with SeqNo " + expected.getSequenceNumber() + " was not added to the ReadIndex.", TestReadIndex.APPEND, invokedMethod.methodName);
                    Assert.assertEquals("Append with SeqNo " + expected.getSequenceNumber() + " was added to the ReadIndex with wrong arguments.", expectedKey, invokedMethod.args.get("key"));
                    Assert.assertEquals("Append with SeqNo " + expected.getSequenceNumber() + " was added to the ReadIndex with wrong arguments.", appendOp.getData().length, invokedMethod.args.get("length"));
                } else if (expected instanceof MergeBatchOperation) {
                    MergeBatchOperation mergeOp = (MergeBatchOperation) expected;
                    Assert.assertEquals("Merge with SeqNo " + expected.getSequenceNumber() + " was not added to the ReadIndex.", TestReadIndex.BEGIN_MERGE, invokedMethod.methodName);
                    Assert.assertEquals("Merge with SeqNo " + expected.getSequenceNumber() + " was added to the ReadIndex with wrong arguments.", mergeOp.getStreamSegmentId(), invokedMethod.args.get("targetStreamSegmentId"));
                    Assert.assertEquals("Merge with SeqNo " + expected.getSequenceNumber() + " was added to the ReadIndex with wrong arguments.", mergeOp.getTargetStreamSegmentOffset(), invokedMethod.args.get("offset"));
                    Assert.assertEquals("Merge with SeqNo " + expected.getSequenceNumber() + " was added to the ReadIndex with wrong arguments.", mergeOp.getBatchStreamSegmentId(), invokedMethod.args.get("sourceStreamSegmentId"));
                }
            }
        }

        // Test DataCorruptionException.
        AssertExtensions.assertThrows(
                "MemoryLogUpdater accepted an operation that was out of order.",
                () -> updater.add(new MergeBatchOperation(1, 2)), // This does not have a SequenceNumber set, so it should trigger a DCE.
                ex -> ex instanceof DataCorruptionException);
    }