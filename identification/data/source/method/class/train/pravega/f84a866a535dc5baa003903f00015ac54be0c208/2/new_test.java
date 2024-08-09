@Test
    @SuppressWarnings("unchecked")
    public void testFlush() throws Exception {
        int segmentCount = 10;
        int operationCountPerType = 5;

        // Add to MTL + Add to ReadIndex (append; beginMerge).
        SequencedItemList<Operation> opLog = new SequencedItemList<>();
        ArrayList<TestReadIndex.MethodInvocation> methodInvocations = new ArrayList<>();
        TestReadIndex readIndex = new TestReadIndex(methodInvocations::add);
        AtomicInteger flushCallbackCallCount = new AtomicInteger();
        MemoryStateUpdater updater = new MemoryStateUpdater(opLog, readIndex, flushCallbackCallCount::incrementAndGet);
        ArrayList<Operation> operations = populate(updater, segmentCount, operationCountPerType);

        methodInvocations.clear(); // We've already tested up to here.
        updater.flush();
        Assert.assertEquals("Unexpected number of calls to the ReadIndex triggerFutureReads method.", 1, methodInvocations.size());
        Assert.assertEquals("Unexpected number of calls to the flushCallback provided in the constructor.", 1, flushCallbackCallCount.get());
        TestReadIndex.MethodInvocation mi = methodInvocations.get(0);
        Assert.assertEquals("No call to ReadIndex.triggerFutureReads() after call to flush().", TestReadIndex.TRIGGER_FUTURE_READS, mi.methodName);
        Collection<Long> triggerSegmentIds = (Collection<Long>) mi.args.get("streamSegmentIds");
        HashSet<Long> expectedSegmentIds = new HashSet<>();
        for (Operation op : operations) {
            if (op instanceof StorageOperation) {
                expectedSegmentIds.add(((StorageOperation) op).getStreamSegmentId());
            }
        }

        AssertExtensions.assertContainsSameElements("ReadIndex.triggerFutureReads() was called with the wrong set of StreamSegmentIds.", expectedSegmentIds, triggerSegmentIds);
    }