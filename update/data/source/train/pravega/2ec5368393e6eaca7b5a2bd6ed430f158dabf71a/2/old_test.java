@Test
    public void testClear() throws Exception {
        int segmentCount = 10;
        int operationCountPerType = 5;

        // Add to MTL + Add to ReadIndex (append; beginMerge).
        MemoryOperationLog opLog = new MemoryOperationLog();
        ArrayList<TestReadIndex.MethodInvocation> methodInvocations = new ArrayList<>();
        TestReadIndex readIndex = new TestReadIndex(methodInvocations::add);
        MemoryLogUpdater updater = new MemoryLogUpdater(opLog, readIndex);
        populate(updater, segmentCount, operationCountPerType);

        methodInvocations.clear(); // We've already tested up to here.
        updater.clear();
        updater.flush();
        Assert.assertEquals("Unexpected size for MemoryOperationLog after calling clear.", 0, opLog.getSize());

        Assert.assertEquals("Unexpected number of calls to the ReadIndex.", 2, methodInvocations.size());
        TestReadIndex.MethodInvocation mi = methodInvocations.get(0);
        Assert.assertEquals("No call to ReadIndex.clear() after call to clear().", TestReadIndex.CLEAR, mi.methodName);

        mi = methodInvocations.get(1);
        Assert.assertEquals("No call to ReadIndex.triggerFutureReads() after call to flush().", TestReadIndex.TRIGGER_FUTURE_READS, mi.methodName);
        Collection<Long> triggerSegmentIds = (Collection<Long>) mi.args.get("streamSegmentIds");
        Assert.assertEquals("Call to ReadIndex.triggerFutureReads() with non-empty collection after call to clear() and flush().", 0, triggerSegmentIds.size());
    }