private Result runQueryOnAllPartitions(Query query) {
        BitSet mutablePartitionIds = getAllPartitionIds();

        Result result = doRunQueryOnQueryThreads(query, mutablePartitionIds, Target.ALL_NODES);
        if (isResultFromAnyPartitionMissing(mutablePartitionIds)) {
            doRunQueryOnPartitionThreads(query, mutablePartitionIds, result);
        }
        assertAllPartitionsQueried(mutablePartitionIds);

        return result;
    }