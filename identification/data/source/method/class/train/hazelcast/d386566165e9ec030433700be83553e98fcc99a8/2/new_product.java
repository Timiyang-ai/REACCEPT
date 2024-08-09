private Result runOnGivenPartition(Query query, Target target) {
        try {
            return dispatchPartitionScanQueryOnOwnerMemberOnPartitionThread(
                    query, target.getPartitionId()).get();
        } catch (Throwable t) {
            throw rethrow(t);
        }
    }