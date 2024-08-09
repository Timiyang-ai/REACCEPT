@Test
    public void testFlush() throws Exception {
        int segmentCount = 10;
        int operationCountPerType = 5;

        // Add to MTL + Add to ReadIndex (append; beginMerge).
        MemoryOperationLog opLog = new MemoryOperationLog();
        ArrayList<TestReadIndex.MethodInvocation> methodInvocations = new ArrayList<>();
        TestReadIndex readIndex = new TestReadIndex(methodInvocations::add);
        MemoryLogUpdater updater = new MemoryLogUpdater(opLog, new CacheUpdater(new InMemoryCache("0"), readIndex));
        ArrayList<Operation> operations = populate(updater, segmentCount, operationCountPerType);

        methodInvocations.clear(); // We've already tested up to here.
        updater.flush();
        Assert.assertEquals("Unexpected number of calls to the ReadIndex.", 1, methodInvocations.size());
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