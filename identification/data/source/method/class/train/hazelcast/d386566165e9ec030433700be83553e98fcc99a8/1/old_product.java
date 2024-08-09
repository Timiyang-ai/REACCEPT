private Result runQueryOnGivenPartition(Query query, Target target) {
        try {
            return queryDispatcher.dispatchPartitionScanQueryOnOwnerMemberOnPartitionThread(
                    query, target.getPartitionId()).get();
        } catch (Throwable t) {
            throw rethrow(t);
        }
    }