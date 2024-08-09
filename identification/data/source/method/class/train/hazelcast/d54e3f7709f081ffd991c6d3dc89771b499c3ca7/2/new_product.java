private Result runOnAllPartitions(Query query) {
        BitSet mutablePartitionIds = getAllPartitionIds();

        Result result = doRunOnQueryThreads(query, mutablePartitionIds, Target.ALL_NODES);
        if (isResultFromAnyPartitionMissing(mutablePartitionIds)) {
            doRunOnPartitionThreads(query, mutablePartitionIds, result);
        }
        assertAllPartitionsQueried(mutablePartitionIds);

        return result;
    }