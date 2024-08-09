public boolean isCompactionPending() {
        //if we have a compaction target, a compaction is pending
        return getCompactionTarget() != null;
    }