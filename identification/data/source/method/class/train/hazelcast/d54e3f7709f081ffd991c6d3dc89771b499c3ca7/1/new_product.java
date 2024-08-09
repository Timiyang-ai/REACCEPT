private Result runOnLocalPartitions(Query query) {
        BitSet mutablePartitionIds = getLocalPartitionIds();

        Result result = doRunOnQueryThreads(query, mutablePartitionIds, Target.LOCAL_NODE);
        if (isResultFromAnyPartitionMissing(mutablePartitionIds)) {
            doRunOnPartitionThreads(query, mutablePartitionIds, result);
        }
        assertAllPartitionsQueried(mutablePartitionIds);

        return result;
    }