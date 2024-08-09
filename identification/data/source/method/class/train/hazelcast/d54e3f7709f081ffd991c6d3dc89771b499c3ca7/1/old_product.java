private Result runQueryOnLocalPartitions(Query query) {
        BitSet mutablePartitionIds = getLocalPartitionIds();

        Result result = doRunQueryOnQueryThreads(query, mutablePartitionIds, Target.LOCAL_NODE);
        if (isResultFromAnyPartitionMissing(mutablePartitionIds)) {
            doRunQueryOnPartitionThreads(query, mutablePartitionIds, result);
        }
        assertAllPartitionsQueried(mutablePartitionIds);

        return result;
    }